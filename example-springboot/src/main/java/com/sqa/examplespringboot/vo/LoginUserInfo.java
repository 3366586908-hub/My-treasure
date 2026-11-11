package com.sqa.examplespringboot.vo;

import lombok.Data;

@Data
public class LoginUserInfo {
    private Long UserId;
    private String nickName;
    private String avatar;
}
