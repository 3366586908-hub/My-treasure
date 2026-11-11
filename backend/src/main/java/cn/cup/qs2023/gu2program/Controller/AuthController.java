package cn.cup.qs2023.gu2program.Controller;

import cn.cup.qs2023.gu2program.Entity.User;
import cn.cup.qs2023.gu2program.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        try {
            User user = userService.registerUser(username, password);
            return ResponseEntity.ok(Map.of("message", "success"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        boolean valid = userService.checkUser(username, password);
        if (valid) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(401).body("failed");
        }
    }
}