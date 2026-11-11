package cn.cup.qs2023.music.Service;

import cn.cup.qs2023.music.dto.request.MenuItemResponse;
import java.util.List;

public interface MenuService {
    List<MenuItemResponse> getMenuByRole(String role);
}
