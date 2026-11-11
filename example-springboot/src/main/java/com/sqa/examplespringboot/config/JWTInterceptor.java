package com.sqa.examplespringboot.config;

import com.sqa.examplespringboot.common.BaseContext;
import com.sqa.examplespringboot.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if ("/error".equals(request.getRequestURI())) {
            return true;
        }
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            response.setStatus(401);
            return false;
        }
        String token = authorization.substring(7);
        try {
            Jws<Claims> claims = JWTUtil.parseToken(token);
            Long userID = Long.valueOf(claims.getPayload().get("userId").toString());
            String nickName = claims.getPayload().get("nickName").toString();
            String avatar = claims.getPayload().get("avatar").toString();
            BaseContext.setUserID(userID);
            BaseContext.setNickName(nickName);
            BaseContext.setAvatar(avatar);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }
}
