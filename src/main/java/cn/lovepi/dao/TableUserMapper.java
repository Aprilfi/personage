package cn.lovepi.dao;

import cn.lovepi.pojo.User;

public interface TableUserMapper extends BaseMapper<User> {

    /**
     * 验证邮箱是否存在，
     * @param userEmail
     * @return
     */
    User validateEmailExist(String userEmail);


    /**被激活了的邮箱才算是真正的用户
     * @param userEmail
     * @return
     */
    User validateUserExist(String userEmail);

}