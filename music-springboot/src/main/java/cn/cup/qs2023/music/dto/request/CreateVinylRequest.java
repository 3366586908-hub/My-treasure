package cn.cup.qs2023.music.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateVinylRequest {
    @NotBlank
    private String artist;
    @NotBlank
    private String title;
    private LocalDate releaseDate;
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal price;
    private String genre;
    private String description;
    private String status = "listed";
}