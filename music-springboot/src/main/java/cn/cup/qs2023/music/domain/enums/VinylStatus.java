package cn.cup.qs2023.music.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum VinylStatus {
    LISTED("listed"),
    UNLISTED("unlisted"),
    DELETED("deleted");

    @EnumValue
    @JsonValue
    private final String value;

    VinylStatus(String value) { this.value = value; }
}