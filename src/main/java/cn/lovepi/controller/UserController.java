package cn.lovepi.controller;

import cn.lovepi.pojo.ActiveUser;
import cn.lovepi.pojo.User;
import cn.lovepi.utils.Captcha;
import cn.lovepi.utils.GifCaptcha;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    /**
     * 邮箱判断，如果存在让用户更换邮箱注册
     * @param userEmail
     * @param out
     */
    @RequestMapping("/validateEmail.do")
    public void validateEmail(String userEmail, PrintWriter out) {
        User user = userService.validateEmailExist(userEmail);

        if(null != user && null != user.getUserEmail()) {
            out.write("hasEmail");
        }
        out.write("noEmail");

    }

    /**
     * 用户登陆：调用数据层进行账号密码判断
     * @param activeUser
     * @param bindingResult
     * @return modelandview
     */
    @RequestMapping(value = "/login.do")
    public ModelAndView login(@Validated ActiveUser activeUser, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        //如果参数不符合规则，返回错误信息
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if(allErrors != null && allErrors.size() > 0){
            /*测试语句*/
            System.out.println(allErrors.get(0));
            modelAndView.setViewName("error");
            modelAndView.addObject("message", allErrors.toString());

            return modelAndView;
        }

        User user1 = userService.validateEmailExist(activeUser.getUserEmail());
        if(user1 != null && user1.getUserEmail().equals(activeUser.getUserEmail())) {
            //将用户输入的密码与随机盐加密生成固定散列值
            Md5Hash md5Hash = new Md5Hash(activeUser.getPassword(),user1.getSalt(),2);
            //进行判断
            if(md5Hash.toString().equals(user1.getUserPassword())) {
                //进一步进行验证码判断
                if(activeUser.getCaptcha().equals(request.getSession().getAttribute("captcha"))) {
                    modelAndView.setViewName("page");

                    return  modelAndView;
                }
                modelAndView.setViewName("error");
                modelAndView.addObject("message", "验证码错误");


                return  modelAndView;

            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("message", "用户名或密码错误");

                return modelAndView;
            }

        }
        modelAndView.setViewName("error");
        modelAndView.addObject("message", "用户名或密码错误");

        return modelAndView;
    }

    /**
     * 用户注册：springvalidator进行后台数据过滤，向客户端返回激活账号的链接
     * @param user
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    public String register(@Validated User user, BindingResult bindingResult) throws Exception {
        //        //如果参数不符合规则，返回错误信息
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if(allErrors != null && allErrors.size() > 0){
            /*测试语句*/
            System.out.println(allErrors.get(0));
            return "数据提交失败";
        }
        //对密码加密后存储到数据库中
        userService.encryptedPassword(user);
        //对用户数据持久化处理
        userService.insert(user);

        //存储完毕返回邮箱激活地址
        String url = getProjectPath() + "/user/activate.do?userId="+user.getUserId();
        emailService.sendEmail(user, "注册", url);
        //获取邮箱地址
        String emailAddress = user.getUserEmail()
                .substring(user.getUserEmail().indexOf("@"), user.getUserEmail().length());
        return emailAddress;
    }

    /**
     * 账号激活，判断注册时间与当前时间在进行账号激活
     * @param userId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "activate.do", method = RequestMethod.GET)
    public ModelAndView activate(String userId) throws Exception {
        //获取未激活的用户资料对象
        User user = userService.selectByPrimaryKey(userId);
        //创建提示信息变量
        String content = "";
        if(user != null) {
            //得到当前时间和邮件时间对比,24小时内
            if(System.currentTimeMillis() - user.getTokenExptime().getTime() < 86400000) {
                //激活用户
                user.setActiState(User.ACTIVATION_SUCCESSFUL);
                //在数据库中更新
                userService.updateByPrimaryKeySelective(user);
                content = "激活成功";
            } else {
                content = "激活失败,激活码已过期";

                //删除过期的用户资料
                userService.deleteByPrimaryKey(user.getUserId());
            }
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("activate");
            modelAndView.addObject("username", user.getUserNickname());
            modelAndView.addObject("content", content);

            return modelAndView;
        }
        return null;
    }

    /**
     * 验证码测试
     * @param request
     * @param captcha
     */
    @RequestMapping(value= "/testcaptcha.do", method = RequestMethod.POST)
    public void testcaptcha(HttpServletRequest request, String captcha) {
        String newCaptcha = (String)request.getSession().getAttribute("captcha");

        System.out.println(captcha);
        System.out.println(newCaptcha);


    }

    /**
     * 生成验证码
     *
     * @param response
     */
    @RequestMapping(value = "/getGifCode.do", method = RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) throws IOException {

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/gif");

        // gif格式动画验证码 宽，高，位数。
        Captcha captcha = new GifCaptcha(146, 42, 4);

        /**
         * 把验证码写到浏览器后才能知道验证码的数据，才能把数据装到session中，在后台会报出异常，我认为这样设计得不好。虽然不影响使用
         * @author ：ozc
         */
        ServletOutputStream out = response.getOutputStream();
        captcha.out(out);
        request.getSession().setAttribute("captcha", captcha.text().toLowerCase());


    }
}
