package cn.cup.qs2023.gu2program.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_feedback")
@DynamicInsert
@DynamicUpdate
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(length = 20)
    private String feedbackStatus = "waiting";

    private String replyContent;

    private LocalDateTime replyTime;

    private LocalDateTime createTime = LocalDateTime.now();
    private LocalDateTime updateTime = LocalDateTime.now();
}