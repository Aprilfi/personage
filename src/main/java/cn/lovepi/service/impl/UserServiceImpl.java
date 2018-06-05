package cn.lovepi.service.impl;

import cn.lovepi.pojo.User;
import cn.lovepi.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl extends BaseServiceImpl<User>
        implements UserService {


    @Override
    public User validateEmailExist(String userEmail) {
        return userMapper.validateEmailExist(userEmail);
    }

    @Override
    public User validateUserExist(String userEmail) {
        return userMapper.validateUserExist(userEmail);
    }

    @Override
    public User encryptedPassword(User user) {
        //生成随机盐
        String salt = UUID.randomUUID().toString();
        //根据随机盐加密密码
        Md5Hash md5Hash = new Md5Hash(user.getUserPassword(), salt, 2);
        //注入到用户资料对象中
        user.setUserPassword(md5Hash.toString());
        user.setSalt(salt);

        return user;
    }
}
