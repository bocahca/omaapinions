package com.testproject.survey.service;

import com.testproject.survey.dto.SurveyQuestionDto;

public interface SurveyQuestionService {
    void createSurveyQuestion(Long surveyId, SurveyQuestionDto sqDto);

    SurveyQuestionDto findSurveyQuestionById(long questionId); 

    public void updateSurveyQuestion(SurveyQuestionDto question);
}
