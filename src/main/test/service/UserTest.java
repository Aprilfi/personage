package service;

import cn.lovepi.pojo.User;
import cn.lovepi.service.UserService;
import org.apache.log4j.BasicConfigurator;
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
        User user = userService.selectByPrimaryKey("1100");

    }

}
