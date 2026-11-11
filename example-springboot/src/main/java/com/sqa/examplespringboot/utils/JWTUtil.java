package com.sqa.examplespringboot.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import java.util.Date;
import java.util.Map;

public class JWTUtil {
    //私钥
    private static final String JWT_SECRET = "qwertyuiop1234567890asdfghjkl1234567890zxcvbnm1234567890";
    //JWT设置的Token的过期时间
    private static final Long JWT_EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 7;

    /**
     * 生成JWT的Token方法
     * 使用的是HS256的加密算法，私钥用固定的JWT_SECRET，过期时间用固定的JWT_EXPIRATION-TIME
     * @param claims
     * @return
     */
    public static String generateToken(Map<String, Object> claims) {
        SecureDigestAlgorithm<SecretKey,SecretKey> algorithm = Jwts.SIG.HS256;
        //过期时间
        long expMillis = System.currentTimeMillis() + JWT_EXPIRATION_TIME;
        Date exp = new Date(expMillis);
        //key就是密钥的对象
        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
        String compact = Jwts.builder().signWith(key,algorithm).expiration(exp).claims(claims).compact();
        return Jwts.builder().signWith(key,algorithm).expiration(exp).claims(claims).compact();
    }

    /**
     * JWT的Token解秘方法，把Token中携带的用户信息解密出来
     * @param token
     * @return
     */
    public static Jws<Claims> parseToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token);

    }
}
