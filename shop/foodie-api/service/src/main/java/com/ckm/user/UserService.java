package com.ckm.user;

import com.ckm.bo.UserBo;
import com.ckm.pojo.Users;
import org.apache.catalina.User;


public interface UserService {

    //校验用户名
    public boolean queryUserNameIsExist(String userName);

    //注册用户
    public Users createrUser(UserBo userBo);

    //登录
    public Users  login(String username,String password);
}
