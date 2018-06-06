package cn.lovepi.controller;

import cn.lovepi.pojo.ActiveUser;
import cn.lovepi.pojo.User;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.omg.CORBA.UserException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.testng.mustache.Model;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @RequestMapping(value = "/login.do")
    public ModelAndView login(@Validated ActiveUser activeUser, BindingResult bindingResult) {
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

        User user1 = userService.selectByPrimaryKey(activeUser.getUserNickname());
        if(user1 != null && user1.getUserNickname().equals(activeUser.getUserNickname())) {

            if(user1.getUserPassword().equals(activeUser.getPassword())) {
                modelAndView.setViewName("page");

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

    @ResponseBody
    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    public String register(@Validated User user, BindingResult bindingResult) throws Exception {
        //如果参数不符合规则，返回错误信息
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
}
