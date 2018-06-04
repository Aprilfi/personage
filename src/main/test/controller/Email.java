package controller;


import cn.lovepi.controller.BaseController;
import cn.lovepi.pojo.User;
import cn.lovepi.service.impl.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/application-context.xml"})
public class Email extends BaseController {

    @Test
    public void sendEmail() throws Exception {
        User user = new User();
        user.setUserEmail("1609938729@qq.com");
        emailService.sendEmail(user,"注册","www.baidu.com");
    }

}
