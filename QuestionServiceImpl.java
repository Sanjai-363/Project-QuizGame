package com.project.quiz.service;

import com.project.quiz.model.Question;
import com.project.quiz.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    
    
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {
        Optional<Question> question = Optional.empty();
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new RuntimeException("Question not found with ID: " + id);
        }
    }

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        Question existingQuestion = questionRepository.save(question);

        existingQuestion.setQuestionText(question.getQuestionText());
        existingQuestion.setOptionA(question.getOptionA());
        existingQuestion.setOptionB(question.getOptionB());
        existingQuestion.setOptionC(question.getOptionC());
        existingQuestion.setOptionD(question.getOptionD());
        existingQuestion.setCorrectAnswer(question.getCorrectAnswer());


        return questionRepository.save(existingQuestion);
    }
    

    @Override
    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new RuntimeException("Question not found with ID: " + id);
        }
        questionRepository.deleteByid(id);
    }
    
    public int checkAnswer(Long questionId, String userAnswer, int currentScore) {
       
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new NoSuchElementException("Question not found"));

        if (question.getCorrectAnswer().equalsIgnoreCase(userAnswer)) {
            currentScore++; 
        }
        return currentScore;
    }


 }