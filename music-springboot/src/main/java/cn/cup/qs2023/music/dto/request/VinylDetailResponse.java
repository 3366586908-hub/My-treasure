
package cn.cup.qs2023.music.dto.request;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VinylDetailResponse {
    private Long id;
    private String artist;
    private String title;
    private LocalDate releaseDate;
    private BigDecimal price;
    private String genre;
    private String coverImagePath;
    private String description;
    private String status;
    private SellerInfo seller;

    @Data
    public static class SellerInfo {
        private Long id;
        private String username;
    }
}