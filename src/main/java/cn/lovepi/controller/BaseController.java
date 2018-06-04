package cn.lovepi.controller;

import cn.lovepi.service.UserService;
import cn.lovepi.service.impl.EmailService;
import cn.lovepi.utils.ReadPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    @Autowired
    protected UserService userService;

    @Autowired
    protected EmailService emailService;

    /**
     * @return 返回配置项目的路径：http://localhost:8080/personage
     */
    public String getProjectPath() {
        return ReadPropertiesUtil.readProp("projectPath");
    }

}
