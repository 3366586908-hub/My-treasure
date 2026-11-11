package cn.cup.qs2023.gu2program.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_course")
@DynamicInsert
@DynamicUpdate
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String courseName;

    @Column(length = 500)
    private String courseDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id")
    private User coach;
    @Column(nullable = false)
    private BigDecimal price;

    @Column(length = 20)
    private String courseType;

    private Integer duration;

    private Integer maxPeople;

    private LocalDateTime createTime = LocalDateTime.now();
    private LocalDateTime updateTime = LocalDateTime.now();

    private Integer status = 1;
}