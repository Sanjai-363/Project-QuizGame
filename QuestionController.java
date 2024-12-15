package com.project.quiz.controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.quiz.dto.AnswerRequest;
import com.project.quiz.dto.ScoreResponse;
import com.project.quiz.model.Question;
import com.project.quiz.service.QuestionService;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins="http://127.0.0.1:5500")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    
    @PostMapping("/submit")
    public ResponseEntity<?> submitAnswer(@RequestBody AnswerRequest answerRequest) {
        int updatedScore = questionService.checkAnswer(answerRequest.getQuestionId(),answerRequest.getUserAnswer(),answerRequest.getCurrentScore());
        return ResponseEntity.ok(new ScoreResponse(updatedScore));
    }

    @GetMapping("/get")
    public List<Question> getQuestions() {
        return questionService.getAllQuestions();
    }
    
    @PutMapping("/update")
    public ResponseEntity<?> updateQuestion(@RequestBody Question question) {
        try {
            return ResponseEntity.ok(questionService.updateQuestion(question));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating the question");
        }
    }
    @PostMapping("/check-answer/{questionId}")
    public ResponseEntity<String> checkAnswer(@PathVariable Long questionId, @RequestBody AnswerRequest answerRequest) {
        
        int updatedScore = questionService.checkAnswer(questionId, answerRequest.getUserAnswer(), answerRequest.getCurrentScore());

        return ResponseEntity.ok("{\"updatedScore\": " + updatedScore + "}");
    }
    
  
  
}
