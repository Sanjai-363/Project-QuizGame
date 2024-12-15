package com.project.quiz.service;

import com.project.quiz.model.Question;
import java.util.List;

public interface QuestionService {
    // Get all questions
    List<Question> getAllQuestions();

    // Get a question by its ID
    Question getQuestionById(Long id);

    // Add a new question
    Question addQuestion(Question question);

    // Update an existing question
    Question updateQuestion(Question question);

    // Delete a question by ID
    void deleteQuestion(Long id);

	int checkAnswer(Long questionId, String userAnswer, int currentScore);

}