package cn.cup.qs2023.music.util;

import cn.cup.qs2023.music.domain.User;
import jakarta.servlet.http.HttpSession;

public class SessionUtils {
    public static final String SESSION_USER_KEY = "currentUser";
    public static void setCurrentUser(HttpSession session, User user) { session.setAttribute(SESSION_USER_KEY, user); }
    public static User getCurrentUser(HttpSession session) { return (User) session.getAttribute(SESSION_USER_KEY); }
    public static void clear(HttpSession session) { session.removeAttribute(SESSION_USER_KEY); }
}
