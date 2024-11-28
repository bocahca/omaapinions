package com.testproject.survey.service;

import java.util.List;

import com.testproject.survey.dto.SurveyDto;
import com.testproject.survey.models.Survey;


public interface SurveyService {
    List<SurveyDto> findAllSurveys();
    public Survey saveSurvey(SurveyDto surveyDto);
    public SurveyDto findSurveyById(long surveyId);
    public void updateSurvey(SurveyDto survey);
    void delete(long surveyId);
    List<SurveyDto> searchSurveys(String query);

}
