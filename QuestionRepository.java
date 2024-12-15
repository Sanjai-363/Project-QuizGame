package com.project.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.quiz.model.Question;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

		void deleteByid(Long id);

}
