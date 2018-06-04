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
        return tableUserMapper.validateEmailExist(userEmail);
    }

    @Override
    public User validateUserExist(String userEmail) {
        return tableUserMapper.validateUserExist(userEmail);
    }

    @Override
    public User encryptedPassword(User user) {

        String salt = UUID.randomUUID().toString();
        Md5Hash md5Hash = new Md5Hash(user.getUserPassword(), salt, 2);
        user.setUserPassword(md5Hash.toString());
        user.setSalt(salt);
        return user;
    }
}
