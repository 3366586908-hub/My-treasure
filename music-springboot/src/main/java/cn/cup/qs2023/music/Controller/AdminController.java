package cn.cup.qs2023.music.Controller;

import cn.cup.qs2023.music.Mapper.UserMapper;
import cn.cup.qs2023.music.Mapper.VinylMapper;
import cn.cup.qs2023.music.domain.User;
import cn.cup.qs2023.music.domain.Vinyl;
import cn.cup.qs2023.music.domain.enums.UserStatus;
import cn.cup.qs2023.music.domain.enums.VinylStatus;
import cn.cup.qs2023.music.util.SessionUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:63343", allowCredentials = "true")
@RestController
@RequestMapping("/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserMapper userMapper;
    private final VinylMapper vinylMapper;

    private void checkAdmin(HttpSession session) {
        User user = SessionUtils.getCurrentUser(session);
        if (user == null || !"admin".equals(user.getRole().getValue())) {
            throw new RuntimeException("No Permission");
        }
    }

    @GetMapping("/dashboard/stats")
    public ResponseEntity<?> getStats(HttpSession session) {
        checkAdmin(session);
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getDeleteFlag, 0)));
        stats.put("totalVinyls", vinylMapper.selectCount(new LambdaQueryWrapper<Vinyl>().eq(Vinyl::getDeleteFlag, 0)));
        stats.put("listedVinyls", vinylMapper.selectCount(new LambdaQueryWrapper<Vinyl>()
                .eq(Vinyl::getDeleteFlag, 0)
                .eq(Vinyl::getStatus, VinylStatus.LISTED)));
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("data", stats);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    public ResponseEntity<?> listUsers(@RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(required = false) String role,
                                       HttpSession session) {
        checkAdmin(session);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().eq(User::getDeleteFlag, 0);
        if (role != null && !role.isEmpty()) {
            wrapper.eq(User::getRole, role);
        }
        IPage<User> userPage = userMapper.selectPage(new Page<>(page, size), wrapper);
        Map<String, Object> result = new HashMap<>();
        result.put("total", userPage.getTotal());
        result.put("records", userPage.getRecords());
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("data", result);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/users/{id}/status")
    public ResponseEntity<?> changeUserStatus(@PathVariable Long id,
                                              @RequestParam String status,
                                              HttpSession session) {
        checkAdmin(session);
        User user = userMapper.selectById(id);
        if (user != null) {
            user.setStatus(UserStatus.valueOf(status.toUpperCase()));
            userMapper.updateById(user);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "Update Success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vinyls")
    public ResponseEntity<?> listVinylsForModeration(@RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     HttpSession session) {
        checkAdmin(session);
        LambdaQueryWrapper<Vinyl> wrapper = new LambdaQueryWrapper<Vinyl>().eq(Vinyl::getDeleteFlag, 0);
        IPage<Vinyl> vinylPage = vinylMapper.selectPage(new Page<>(page, size), wrapper);
        Map<String, Object> result = new HashMap<>();
        result.put("total", vinylPage.getTotal());
        result.put("records", vinylPage.getRecords());
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("data", result);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/vinyls/{id}/hide")
    public ResponseEntity<?> hideVinyl(@PathVariable Long id, HttpSession session) {
        checkAdmin(session);
        Vinyl vinyl = vinylMapper.selectById(id);
        if (vinyl != null) {
            vinyl.setStatus(VinylStatus.UNLISTED);
            vinylMapper.updateById(vinyl);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "hide vinyl");
        return ResponseEntity.ok(response);
    }
}