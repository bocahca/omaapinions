package com.testproject.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testproject.survey.models.SurveyQuestion;

// repository - nge extends JPA Repository biar gausah make querry buat akses CRUD, tapi bisa nambahin custom querry juga

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Long> {
    
}
