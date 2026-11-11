
package cn.cup.qs2023.music.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserRole {
    USER("user"),
    RETAILER("retailer"),
    ADMIN("admin");

    @EnumValue
    @JsonValue
    private final String value;

    UserRole(String value) { this.value = value; }
}