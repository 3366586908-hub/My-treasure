package com.sqa.examplespringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqa.examplespringboot.entity.User;
import com.sqa.examplespringboot.vo.LoginUserInfo;


public interface UserService extends IService<User> {
    Integer register(User user);
    String login(String email,String password);

    LoginUserInfo getLoginUserInfo();

    Integer changePassword(String oldPassword, String newPassword);
}
