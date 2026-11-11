package cn.cup.qs2023.music.Handler;

import cn.cup.qs2023.music.domain.User;
import cn.cup.qs2023.music.util.SessionUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private Long getCurrentUserId() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) return 0L;
            HttpServletRequest request = attributes.getRequest();
            if (request == null) return 0L;
            User user = SessionUtils.getCurrentUser(request.getSession(false));
            return user != null ? user.getId() : 0L;
        } catch (Exception e) {
            return 0L;
        }
    }

    private String getCurrentUserName() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) return "SYSTEM";
            HttpServletRequest request = attributes.getRequest();
            if (request == null) return "SYSTEM";
            User user = SessionUtils.getCurrentUser(request.getSession(false));
            return user != null ? user.getUsername() : "SYSTEM";
        } catch (Exception e) {
            return "SYSTEM";
        }
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createAt", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateAt", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createById", Long.class, getCurrentUserId());
        this.strictInsertFill(metaObject, "createByName", String.class, getCurrentUserName());
        this.strictInsertFill(metaObject, "updateById", Long.class, getCurrentUserId());
        this.strictInsertFill(metaObject, "updateByName", String.class, getCurrentUserName());
        this.strictInsertFill(metaObject, "deleteFlag", Integer.class, 0);
        this.strictInsertFill(metaObject, "sortCode", Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateAt", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updateById", Long.class, getCurrentUserId());
        this.strictUpdateFill(metaObject, "updateByName", String.class, getCurrentUserName());
    }
}