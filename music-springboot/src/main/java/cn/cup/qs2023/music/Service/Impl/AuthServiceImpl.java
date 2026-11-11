package cn.cup.qs2023.music.Service.Impl;

import cn.cup.qs2023.music.Mapper.UserMapper;
import cn.cup.qs2023.music.Service.AuthService;
import cn.cup.qs2023.music.domain.User;
import cn.cup.qs2023.music.domain.enums.UserRole;
import cn.cup.qs2023.music.domain.enums.UserStatus;
import cn.cup.qs2023.music.dto.request.LoginRequest;
import cn.cup.qs2023.music.dto.request.RegisterRequest;
import cn.cup.qs2023.music.util.BCryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;

    @Override
    public User authenticate(LoginRequest request) {
        User user = userMapper.selectOne(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<User>()
                        .eq(User::getUsername, request.getUsername())
                        .eq(User::getDeleteFlag, 0)
        );

        if (user == null || !BCryptUtil.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Username or password is incorrect");
        }
        if (user.getStatus() == UserStatus.SUSPENDED) {
            throw new RuntimeException("Account has been suspended");
        }
        return user;
    }

    @Override
    public void register(RegisterRequest request) {
        long count = userMapper.selectCount(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<User>()
                        .eq(User::getUsername, request.getUsername())
                        .or().eq(User::getEmail, request.getEmail())
        );
        if (count > 0) throw new RuntimeException("User already exist");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordHash(BCryptUtil.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(request.getIsRetailer() ? UserRole.RETAILER : UserRole.USER);
        user.setStatus(UserStatus.NORMAL);
        userMapper.insert(user);
    }
}
