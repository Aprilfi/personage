package cn.lovepi.controller;

import cn.lovepi.pojo.User;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.omg.CORBA.UserException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController extends BaseController {

    @RequestMapping("/register.do")
    public String register(@Validated User user, BindingResult bindingResult) throws Exception {
        //如果参数不对，就直接返回注册页面
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if (allErrors != null && allErrors.size() > 0) {
            return "";
        }

        //对密码进行加密md5(密码+salt)后才存到数据库中
        userService.encryptedPassword(user);

        userService.insert(user);

        //提示用户发送了邮件，让用户激活账户
        String url = getProjectPath() + "/user/activate.do?userId=" + user.getUserId();
        emailService.sendEmail(user, "注册", url);

        return "redirect:/common/countDown.html";
    }

}
