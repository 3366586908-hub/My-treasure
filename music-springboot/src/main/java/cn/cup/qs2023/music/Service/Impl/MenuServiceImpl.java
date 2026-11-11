package cn.cup.qs2023.music.Service.Impl;


import cn.cup.qs2023.music.Service.MenuService;
import cn.cup.qs2023.music.dto.request.MenuItemResponse;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {
    @Override
    public List<MenuItemResponse> getMenuByRole(String role) {
        List<MenuItemResponse> menus = new ArrayList<>();
        menus.add(new MenuItemResponse("Page", "/index.html", null));
        menus.add(new MenuItemResponse("Find Vinyl", "/search.html", null));

        if ("user".equals(role) || "retailer".equals(role)) {
            List<MenuItemResponse> userMenus = Arrays.asList(
                    new MenuItemResponse("My Home", "/user/dashboard.html", null),
                    new MenuItemResponse("My Vinyl", "/user/my-listings.html", null)
            );
            menus.add(new MenuItemResponse("Mine", "#", userMenus));
        }
        if ("admin".equals(role)) {
            List<MenuItemResponse> adminMenus = Arrays.asList(
                    new MenuItemResponse("Controller", "/admin/dashboard.html", null),
                    new MenuItemResponse("User Controller", "/admin/users.html", null),
                    new MenuItemResponse("Vinyl Review", "/admin/vinyls.html", null)
            );
            menus.add(new MenuItemResponse("Control", "#", adminMenus));
        }
        return menus;
    }
}