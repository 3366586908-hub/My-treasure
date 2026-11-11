package cn.cup.qs2023.gu2program.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_appointment")
@DynamicInsert
@DynamicUpdate
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private LocalDateTime appointTime;

    @Column(length = 20)
    private String appointStatus;

    @Column(columnDefinition = "TEXT")
    private String remark;

    private LocalDateTime createTime = LocalDateTime.now();
    private LocalDateTime updateTime = LocalDateTime.now();
}
