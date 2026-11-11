package cn.cup.qs2023.music.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserStatus {
    NORMAL("normal"),
    PENDING_RETAILER("pending_retailer"),
    SUSPENDED("suspended");

    @EnumValue
    @JsonValue
    private final String value;

    UserStatus(String value) { this.value = value; }
}