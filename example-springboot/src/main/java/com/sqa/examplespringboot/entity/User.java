package com.sqa.examplespringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("users")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String nickName;

    private String email;

    private String password;

    private Date createDate;

    private Integer status;

    private String salt;

    private String avatar = "images/default_avatar.png";
}
