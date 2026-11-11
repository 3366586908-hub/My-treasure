package cn.cup.qs2023.gu2program.Service.Impl;

import cn.cup.qs2023.gu2program.Entity.User;
import cn.cup.qs2023.gu2program.Repository.UserRepository;
import cn.cup.qs2023.gu2program.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("用户名已存在！");
        }
        User user = new User(username, passwordEncoder.encode(password), "user");
        return userRepository.save(user);
    }

    @Override
    public boolean checkUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}