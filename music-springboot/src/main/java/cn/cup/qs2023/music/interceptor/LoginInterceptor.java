package cn.cup.qs2023.music.interceptor;

import cn.cup.qs2023.music.util.SessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/admin") || uri.contains("/vinyls/mine") || uri.contains("/vinyls") && "POST".equalsIgnoreCase(request.getMethod())) {
            HttpSession session = request.getSession(false);
            if (session == null || SessionUtils.getCurrentUser(session) == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"code\":401,\"message\":\"Please login first\"}");
                return false;
            }
        }
        return true;
    }
}