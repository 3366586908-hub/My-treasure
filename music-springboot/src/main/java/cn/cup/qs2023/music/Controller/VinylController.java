package cn.cup.qs2023.music.Controller;

import cn.cup.qs2023.music.Service.VinylService;
import cn.cup.qs2023.music.domain.User;
import cn.cup.qs2023.music.domain.Vinyl;
import cn.cup.qs2023.music.dto.request.CreateVinylRequest;
import cn.cup.qs2023.music.dto.request.PageResult;
import cn.cup.qs2023.music.dto.request.UpdateVinylRequest;
import cn.cup.qs2023.music.dto.request.VinylDetailResponse;
import cn.cup.qs2023.music.util.SessionUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:63343", allowCredentials = "true")
@RestController
@RequestMapping("/v1/vinyls")
@RequiredArgsConstructor
public class VinylController {
    private final VinylService vinylService;

    @GetMapping
    public ResponseEntity<?> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String genre,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        IPage<Vinyl> vinylPage = vinylService.searchVinyls(keyword, genre, page, size);
        PageResult<Vinyl> result = new PageResult<>(vinylPage.getTotal(), vinylPage.getRecords());
        return ResponseEntity.ok(Map.of("code", 200, "data", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        VinylDetailResponse detail = vinylService.getVinylDetail(id);
        return ResponseEntity.ok(Map.of("code", 200, "data", detail));
    }

    @GetMapping("/mine")
    public ResponseEntity<?> getMyListings(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpSession session) {
        User user = SessionUtils.getCurrentUser(session);
        IPage<Vinyl> myListings = vinylService.getMyListings(user.getId(), page, size);
        PageResult<Vinyl> result = new PageResult<>(myListings.getTotal(), myListings.getRecords());
        return ResponseEntity.ok(Map.of("code", 200, "data", result));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateVinylRequest request, HttpSession session) {
        User user = SessionUtils.getCurrentUser(session);
        Vinyl vinyl = vinylService.createVinyl(user.getId(), request);
        return ResponseEntity.ok(Map.of("code", 200, "message", "successfully", "data", vinyl));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody UpdateVinylRequest request,
                                    HttpSession session) {
        User user = SessionUtils.getCurrentUser(session);
        Vinyl vinyl = vinylService.updateVinyl(id, user.getId(), request);
        return ResponseEntity.ok(Map.of("code", 200, "message", "update successful", "data", vinyl));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, HttpSession session) {
        User user = SessionUtils.getCurrentUser(session);
        vinylService.deleteVinyl(id, user.getId());
        return ResponseEntity.ok(Map.of("code", 200, "message", "delete successful"));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> changeStatus(@PathVariable Long id,
                                          @RequestParam String status,
                                          HttpSession session) {
        User user = SessionUtils.getCurrentUser(session);
        vinylService.changeVinylStatus(id, user.getId(), status);
        return ResponseEntity.ok(Map.of("code", 200, "message", "status successful"));
    }
}