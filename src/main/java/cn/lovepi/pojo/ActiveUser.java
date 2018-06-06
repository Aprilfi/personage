package cn.lovepi.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 存储用户的身份信息
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
public class ActiveUser implements Serializable {
    private String userId;
    private String userEmail;

    @NotNull(message="{user.not.null}")
    @Size(min=4,max=12,message="{user.userNickname.length.error}")
    private String userNickname;

    @NotNull(message="{user.not.null}")
    @Size(min=6,max=12,message="{user.userPassword.length.error}")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
}
