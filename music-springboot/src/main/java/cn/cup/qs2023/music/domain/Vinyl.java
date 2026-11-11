package cn.cup.qs2023.music.domain;

import cn.cup.qs2023.music.domain.enums.VinylStatus;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mo_vinyl")
public class Vinyl extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long sellerId;
    private String artist;
    private String title;
    private LocalDate releaseDate;
    private BigDecimal price;
    private String genre;
    private String coverImagePath;
    private String description;
    private VinylStatus status;
}