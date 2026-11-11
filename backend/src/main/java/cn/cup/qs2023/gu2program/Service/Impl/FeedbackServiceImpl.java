package cn.cup.qs2023.gu2program.Service.Impl;


import cn.cup.qs2023.gu2program.Entity.Feedback;
import cn.cup.qs2023.gu2program.Repository.FeedbackRepository;
import cn.cup.qs2023.gu2program.Service.FeedbackService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not exists：ID=" + id));
    }

    @Override
    public List<Feedback> getFeedbacksByUserId(Long userId) {
        return feedbackRepository.findByUserIdOrderByCreateTimeDesc(userId);
    }

    @Override
    public List<Feedback> getFeedbacksByStatus(String status) {
        if (status == null) {
            status = "waiting";
        }
        return feedbackRepository.findByFeedbackStatusOrderByCreateTimeDesc(status);
    }

    @Override
    public void replyFeedback(Long id, String replyContent) {
        Feedback feedback = getFeedbackById(id);
        feedback.setReplyContent(replyContent);
        feedback.setFeedbackStatus("done");
        feedback.setReplyTime(LocalDateTime.now());
        feedback.setUpdateTime(LocalDateTime.now());
        feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        if (!feedbackRepository.existsById(id)) {
            throw new EntityNotFoundException("not exists：ID=" + id);
        }
        feedbackRepository.deleteById(id);
    }
}
