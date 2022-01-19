package com.ckm.user.impl;

import com.ckm.bo.UserBo;
import com.ckm.dao.UsersMapper;
import com.ckm.enums.Sex;
import com.ckm.pojo.Users;
import com.ckm.user.UserService;
import com.ckm.util.DateUtil;
import com.ckm.util.MD5Utils;
import com.oracle.jrockit.jfr.UseConstantPool;
import org.apache.catalina.User;
import org.apache.commons.lang3.time.DateUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    //默认用户头像
    private static final String USER_FACE="http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean queryUserNameIsExist(String userName) {

        Users user = new Users();
        user.setUsername(userName);
        Users users = usersMapper.selectOne(user);

        return users==null?false:true;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Users createrUser(UserBo userBo) {
        try {
            Users users = new Users();
            users.setId(sid.nextShort());
            users.setUsername(userBo.getUsername());
            users.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
            users.setNickname(userBo.getUsername());
            //设置用户头像
            users.setFace(USER_FACE);
            users.setBirthday(DateUtil.stringToDate("1900-01-01"));
            users.setSex(Sex.secret.type);
            users.setCreatedTime(new Date());
            users.setUpdatedTime(new Date());
            usersMapper.insert(users);
            return users;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Users login(String username, String password) {
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);
        return  usersMapper.selectOne(users);
    }


}
