package cn.cup.qs2023.music.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public static String encode(String raw) { return encoder.encode(raw); }
    public static boolean matches(String raw, String encoded) { return encoder.matches(raw, encoded); }
}
