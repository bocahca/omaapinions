package com.testproject.survey.service;

import java.util.List;

import com.testproject.survey.dto.SurveyDto;
import com.testproject.survey.models.Survey;


// interface service - isinya abstract method buat method-method apa aja yang bisa digunain class nya

public interface SurveyService {
    List<SurveyDto> findAllSurveys();
    public Survey saveSurvey(SurveyDto surveyDto);
    public SurveyDto findSurveyById(long surveyId);
    public void updateSurvey(SurveyDto survey);
    void delete(long surveyId);
    List<SurveyDto> searchSurveys(String query);

}
