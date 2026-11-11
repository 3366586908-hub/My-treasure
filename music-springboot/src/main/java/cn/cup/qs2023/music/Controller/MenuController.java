package cn.cup.qs2023.music.Controller;

import cn.cup.qs2023.music.Service.MenuService;
import cn.cup.qs2023.music.domain.User;
import cn.cup.qs2023.music.dto.request.MenuItemResponse;
import cn.cup.qs2023.music.util.SessionUtils;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:63343", allowCredentials = "true")
@RestController
@RequestMapping("/v1/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<?> getMenu(HttpSession session) {
        User user = SessionUtils.getCurrentUser(session);
        String role = (user == null) ? "guest" : user.getRole().getValue();
        List<MenuItemResponse> menus = menuService.getMenuByRole(role);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", menus);
        return ResponseEntity.ok(result);
    }
}