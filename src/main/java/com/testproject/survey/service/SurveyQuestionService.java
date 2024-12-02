package com.testproject.survey.service;

import com.testproject.survey.dto.SurveyQuestionDto;

// interface service - isinya abstract method buat method-method apa aja yang bisa digunain class nya

public interface SurveyQuestionService {
    void createSurveyQuestion(Long surveyId, SurveyQuestionDto sqDto);

    SurveyQuestionDto findSurveyQuestionById(long questionId); 

    public void updateSurveyQuestion(SurveyQuestionDto question);

    public void deleteQuestion(long questionId);
}
