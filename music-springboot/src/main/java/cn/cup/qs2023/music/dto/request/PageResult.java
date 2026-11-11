
package cn.cup.qs2023.music.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class PageResult<T> {
    private long total;
    private List<T> records;
}
