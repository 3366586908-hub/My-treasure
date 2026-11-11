package cn.cup.qs2023.gu2program.Controller;


import cn.cup.qs2023.gu2program.Entity.Feedback;
import cn.cup.qs2023.gu2program.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;


    @PostMapping
    public ResponseEntity<Feedback> saveFeedback(@RequestBody Feedback feedback) {
        Feedback savedFeedback = feedbackService.saveFeedback(feedback);
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.getFeedbackById(id));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Feedback>> getFeedbacksByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(feedbackService.getFeedbacksByUserId(userId));
    }


    @GetMapping("/status/{status}")
    public ResponseEntity<List<Feedback>> getFeedbacksByStatus(@PathVariable String status) {
        return ResponseEntity.ok(feedbackService.getFeedbacksByStatus(status));
    }


    @PutMapping("/{id}/reply")
    public ResponseEntity<Void> replyFeedback(
            @PathVariable Long id,
            @RequestParam String replyContent) {
        feedbackService.replyFeedback(id, replyContent);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
