package cn.lovepi.service.impl;

import cn.lovepi.pojo.User;
import cn.lovepi.service.UserService;
import cn.lovepi.utils.Base64Util;
import cn.lovepi.utils.FreeMarkerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * 邮件服务类，提供发送邮件的功能
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage simpleMailMessage;

    @Autowired
    private UserService userService;


    /**
     * 使用mimeMessage发送的HTML格式的邮件。
     * @param user
     * @param content
     * @throws Exception
     */
    public void sendEmail(User user, String content, String url) throws Exception {

        String returnText = createSendData(user, content,url);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(simpleMailMessage.getFrom());
        messageHelper.setSubject(simpleMailMessage.getSubject());

        //接受人
        messageHelper.setTo(user.getUserEmail());

        //内容，是HTML格式
        messageHelper.setText(returnText, true);
        mailSender.send(mimeMessage);

    }

    /**
     * 使用freemarker创建要发送的邮件内容
     * @param user    封装了要发送邮件的信息
     * @param content  发送的目的是什么（一个模版、多种邮件)
     * @param url       操作的地址路径是什么
     * @return HTML页面的内容
     * @throws Exception
     */

    public String createSendData(User user, String content,String url) throws Exception {

        Map<String, Object> map = new HashMap();
        map.put("nickName", user.getUserNickname());
        map.put("content", content);
        map.put("url", url);
        map.put("encodeUrl", Base64Util.encodeData(url));

        String returnText = new FreeMarkerUtils().returnText("email.ftl", map);
        System.out.println(returnText);
        return returnText;
    }

}
