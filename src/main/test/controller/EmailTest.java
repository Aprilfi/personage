package controller;

import cn.lovepi.pojo.User;
import cn.lovepi.service.UserService;
import cn.lovepi.service.impl.EmailService;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Console;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/application-context.xml"})
public class EmailTest {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmail() throws Exception {
        BasicConfigurator.configure();
        User user = userService.selectByPrimaryKey("e24ae1db-6892-11e8-82b5-0021cc5f0e0e");
        user.setUserEmail("1609938729@qq.com");
        System.out.println(user.toString());
        emailService.sendEmail(user, "注册", "https://www.baidu.com/");
    }

}
