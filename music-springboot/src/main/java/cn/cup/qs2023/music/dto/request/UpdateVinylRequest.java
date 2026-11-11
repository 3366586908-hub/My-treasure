package cn.cup.qs2023.music.dto.request;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpdateVinylRequest {
    private String artist;
    private String title;
    private LocalDate releaseDate;
    @DecimalMin(value = "0.01")
    private BigDecimal price;
    private String genre;
    private String description;
    private String status;
}
