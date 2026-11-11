
package cn.cup.qs2023.music.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponse {
    private String label;
    private String url;
    private List<MenuItemResponse> children;
}
