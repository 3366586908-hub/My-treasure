package cn.cup.qs2023.music.Controller;

import cn.cup.qs2023.music.Service.AuthService;
import cn.cup.qs2023.music.domain.User;
import cn.cup.qs2023.music.dto.request.LoginRequest;
import cn.cup.qs2023.music.dto.request.LoginResponse;
import cn.cup.qs2023.music.dto.request.RegisterRequest;
import cn.cup.qs2023.music.util.SessionUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:63343", allowCredentials = "true")
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok(Map.of("code", 200, "message", "Register successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        User user = authService.authenticate(request);
        session.setAttribute(SessionUtils.SESSION_USER_KEY, user);
        return ResponseEntity.ok(Map.of(
                "code", 200,
                "message", "Login successfully!",
                "data", new LoginResponse(user.getUsername(), user.getRole().getValue())
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("code", 200, "message", "exit"));
    }
}