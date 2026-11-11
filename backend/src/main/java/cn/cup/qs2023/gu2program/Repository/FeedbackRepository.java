package cn.cup.qs2023.gu2program.Repository;


import cn.cup.qs2023.gu2program.Entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByUserIdOrderByCreateTimeDesc(Long userId);

    List<Feedback> findByFeedbackStatusOrderByCreateTimeDesc(String status);
}
