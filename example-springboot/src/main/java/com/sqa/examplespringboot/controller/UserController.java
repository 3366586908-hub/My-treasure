package com.sqa.examplespringboot.controller;

import com.sqa.examplespringboot.common.Result;
import com.sqa.examplespringboot.common.ResultCode;
import com.sqa.examplespringboot.entity.User;
import com.sqa.examplespringboot.service.UserService;
import com.sqa.examplespringboot.vo.LoginUserInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public Result<User>  register(User user){
        Integer result = userService.register(user);
        if(result==0){
            return Result.failed(ResultCode.USER_HAS_EXISTED);

        }
        if(result==1){
            return Result.success();
        }
        return Result.failed();
    }

    @PostMapping("/login")
    @ResponseBody
    public Result<String>  login(String email,String password){
        String result = userService.login(email,password);
        if(result.equals("0")){
            return Result.failed(ResultCode.USER_NOT_EXIST);
        }
        if(result.equals("2")){
            return Result.failed(ResultCode.USER_PASSWORD_ERROR);

        }
        return Result.success(result);
    }

    @GetMapping("/getLoginUserInfo")
    @ResponseBody
    public Result<LoginUserInfo>  getLoginUserInfo(){
        LoginUserInfo loginUserInfo = userService.getLoginUserInfo();
        return Result.success(loginUserInfo);
    }

    @PostMapping("/changePassword")
    @ResponseBody
    public Result<User>  changePassword(String oldPassword,String newPassword){
        Integer status = userService.changePassword(oldPassword,newPassword);
        if(status==0){
           return Result.failed(ResultCode.USER_PASSWORD_ERROR);
        }else if(status==1){
           return Result.failed();
        }
        return Result.success();
    }
}
