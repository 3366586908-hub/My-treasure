package cn.cup.qs2023.gu2program.Service;

import cn.cup.qs2023.gu2program.Entity.Feedback;
import java.util.List;

public interface FeedbackService {
    Feedback saveFeedback(Feedback feedback);
    Feedback getFeedbackById(Long id);
    List<Feedback> getFeedbacksByUserId(Long userId);
    List<Feedback> getFeedbacksByStatus(String status);
    void replyFeedback(Long id, String replyContent);
    void deleteFeedback(Long id);
}
