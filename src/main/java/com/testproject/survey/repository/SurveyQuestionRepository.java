package com.testproject.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testproject.survey.models.SurveyQuestion;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Long> {
    
}
