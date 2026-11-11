package cn.cup.qs2023.music.Service;

import cn.cup.qs2023.music.domain.User;
import cn.cup.qs2023.music.dto.request.LoginRequest;
import cn.cup.qs2023.music.dto.request.RegisterRequest;

public interface AuthService {
    User authenticate(LoginRequest request);
    void register(RegisterRequest request);
}
