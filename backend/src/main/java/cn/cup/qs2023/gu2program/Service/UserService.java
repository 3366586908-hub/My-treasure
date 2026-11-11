package cn.cup.qs2023.gu2program.Service;

import cn.cup.qs2023.gu2program.Entity.User;

public interface UserService {
    User registerUser(String username, String password);
    boolean checkUser(String username, String password);
    User saveUser(User user);
}