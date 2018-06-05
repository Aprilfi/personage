package cn.lovepi.controller;

import cn.lovepi.pojo.User;
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

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

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
        String url = getProjectPath() + "/user/active?userId="+user.getUserId();
        emailService.sendEmail(user, "注册", url);
        //获取邮箱地址
        String emailAddress = user.getUserEmail()
                .substring(user.getUserEmail().indexOf("@"), user.getUserEmail().length());
        return emailAddress;
    }
}
