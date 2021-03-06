package service;

import cn.lovepi.pojo.User;
import cn.lovepi.service.UserService;
import org.apache.log4j.BasicConfigurator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/application-context.xml"})
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {

        BasicConfigurator.configure();
        User user = new User();
        user.setUserEmail("123123@qq.com");
        user.setUserPassword("123123");
        user.setUserNickname("april");

        userService.encryptedPassword(user);

        userService.insert(user);

    }

    @Test
    public void validatorEmail() {
        BasicConfigurator.configure();
        userService.validateEmailExist("1609938729@qq.com");
    }

    @Test
    public void passw() {
        BasicConfigurator.configure();
        Md5Hash md5Hash = new Md5Hash("123123","456aaf4f-e5bb-44c2-a486-edf419d0e046",2);
        System.out.println(md5Hash.toString());
    }

}
