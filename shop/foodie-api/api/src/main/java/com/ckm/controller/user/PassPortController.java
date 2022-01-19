package com.ckm.controller.user;

import com.ckm.bo.UserBo;
import com.ckm.pojo.Users;
import com.ckm.user.UserService;
import com.ckm.util.CookieUtils;
import com.ckm.util.JSONResult;
import com.ckm.util.JsonUtils;
import com.ckm.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "注册登录",tags = {"用户注册登陆的相关接口"})
@RestController
@RequestMapping("/passport")
public class PassPortController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public JSONResult usernameIsExist(@RequestParam String username){
        try {
            //1.判断用户名是否存在
            if(StringUtils.isBlank(username)){
                return JSONResult.errorMsg("用户名不能为空！");
            }
            //2.查找注册的用户名是否存在
            if(userService.queryUserNameIsExist(username)){
                return JSONResult.errorMsg("用户名已存在！");
        }
            //请求成功
            return JSONResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }

    @ApiOperation(value = "用户注册",notes = "用户注册",httpMethod = "POST")
    @PostMapping("/regist")
    public JSONResult regist(@RequestBody UserBo userBo){
        try {

            String userName = userBo.getUsername();
            String password = userBo.getPassword();
            String confirmPwd = userBo.getConfirmPassword();
            //1.判断用户名/密码不能为空
            if(StringUtils.isBlank(userName)||
             StringUtils.isBlank(password)||
             StringUtils.isBlank(confirmPwd)){
                return JSONResult.errorMsg("用户名或密码不能为空！");
            }
            //2.查询用户名是否存在
            if(userService.queryUserNameIsExist(userName)){
                return JSONResult.errorMsg("用户名已存在！");
            }
            //3.密码长度不能小于6
            if(password.length()>6){
                return JSONResult.errorMsg("密码不能小于6位！");
            }
            //4.密码是否一致
            if(!password.equals(confirmPwd)){
                return JSONResult.errorMsg("输入密码不一致！");
            }

            Users users = userService.createrUser(userBo);

            return JSONResult.ok(users);
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }


    }


    @ApiOperation(value = "用户登录",notes = "用户登录",httpMethod = "POST")
    @PostMapping("/login")
    public JSONResult login(@RequestBody UserBo userBo, HttpServletRequest request,
    HttpServletResponse response){
        try {

            String userName = userBo.getUsername();
            String password = userBo.getPassword();

            //1.判断用户名/密码不能为空
            if(StringUtils.isBlank(userName)||
                    StringUtils.isBlank(password)){
                return JSONResult.errorMsg("用户名或密码不能为空！");
            }
            Users result = userService.login(userName, MD5Utils.getMD5Str(password));
            if(result==null){
                return JSONResult.errorMsg("用户名或密码不正确！");
            }

            //2.删除敏感信息
            result = setNullProperty(result);

            //3.设置会话给前端
            CookieUtils.setCookie(request,response, "user",
                    JsonUtils.objectToJson(result),true);
            //TODO 生成用户token，存入到redis会话
            //TODO 同步购物车数据

            return JSONResult.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }


    private Users setNullProperty(Users userResult){
        userResult.setPassword(null);
        userResult.setMobile(null);
        return userResult;
    }


    @ApiOperation(value = "用户退出登录",notes = "用户退出登录",httpMethod = "POST")
    @PostMapping("/logout")
    public JSONResult logout(@RequestParam String userId,HttpServletRequest request,
    HttpServletResponse response){
        try {
            //1.删除会话
            CookieUtils.deleteCookie(request,response,"user");

            //TODO 用户退出登录需要清空购物车
            //TODO 分布式会话需要清空用户数据

            return JSONResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }


    }



}
