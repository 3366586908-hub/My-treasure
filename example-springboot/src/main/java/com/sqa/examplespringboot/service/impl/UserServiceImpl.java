package com.sqa.examplespringboot.service.impl;

import ch.qos.logback.core.util.MD5Util;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqa.examplespringboot.common.BaseContext;
import com.sqa.examplespringboot.entity.User;
import com.sqa.examplespringboot.mapper.UserMapper;
import com.sqa.examplespringboot.service.UserService;
import com.sqa.examplespringboot.utils.JWTUtil;
import com.sqa.examplespringboot.vo.LoginUserInfo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Integer register(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",user.getEmail());
        boolean isExists = this.exists(queryWrapper);
        if(isExists){
            return 0;
        }

        String password = user.getPassword();
        String salt = (int)(Math.random() * 1000000 + 1)+"";
        String newPassword = MD5.create().digestHex(password + salt);

        Date now = new Date();
        user.setCreateDate(now);
        user.setStatus(1);
        user.setSalt(salt);
        user.setPassword(newPassword);
        boolean flag = this.save(user);
        if(flag){
            return 1;
        }
        return 2;
    }

    @Override
    public String login(String email, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        User dbUser = this.getOne(queryWrapper);
        if(dbUser==null){
            return "0";
        }
        String newPassword = MD5.create().digestHex(password + dbUser.getSalt());
        if(dbUser.getPassword().equals(newPassword)){
            Map<String,Object> map = new HashMap<>();
            map.put("userId",dbUser.getId());
            map.put("nickName",dbUser.getNickName());
            map.put("avatar",dbUser.getAvatar());
            String token = JWTUtil.generateToken(map);
            return token;
        }
        return "2";
    }

    @Override
    public LoginUserInfo getLoginUserInfo() {
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setUserId(BaseContext.getUserID());
        loginUserInfo.setNickName(BaseContext.getNickName());
        loginUserInfo.setAvatar(BaseContext.getAvatar());
        return loginUserInfo;
    }

    @Override
    public Integer changePassword(String oldPassword, String newPassword) {
        long userId = BaseContext.getUserID();
        User dbUser = this.getById(userId);
        String md5OldPassword = MD5.create().digestHex(oldPassword + dbUser.getSalt());
        if(!md5OldPassword.equals(dbUser.getPassword())){
            return 0;
        }
        String md5NewPassword = MD5.create().digestHex(newPassword + dbUser.getSalt());
        dbUser.setPassword(md5NewPassword);
        boolean flag = this.updateById(dbUser);
        if(flag){
            return 2;
        }
        return 1;
    }
}
