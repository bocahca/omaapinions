package com.testproject.survey.service.impl;

import org.springframework.stereotype.Service;

import com.testproject.survey.dto.SurveyQuestionDto;
import static com.testproject.survey.mapper.SurveyQuestionMapper.mapToSurveyQuestion;
import static com.testproject.survey.mapper.SurveyQuestionMapper.mapToSurveyQuestionDto;
import com.testproject.survey.models.Survey;
import com.testproject.survey.models.SurveyQuestion;
import com.testproject.survey.repository.SurveyQuestionRepository;
import com.testproject.survey.repository.SurveyRepository;
import com.testproject.survey.service.SurveyQuestionService;

//service implementation - implementasi dari interface service yang dibuat

@Service
public class SurveyQuestionServiceImpl implements SurveyQuestionService{
    @SuppressWarnings("FieldMayBeFinal")
    private SurveyQuestionRepository sqRepository;
    @SuppressWarnings("FieldMayBeFinal")
    private SurveyRepository surveyRepository;

    
    public SurveyQuestionServiceImpl(SurveyQuestionRepository sqRepository, SurveyRepository surveyRepository) {
        this.sqRepository = sqRepository;
        this.surveyRepository = surveyRepository;
    }
    
    @Override
    public void createSurveyQuestion(Long surveyId, SurveyQuestionDto sqDto) {
        Survey survey = surveyRepository.findById(surveyId).get();
        SurveyQuestion sq = mapToSurveyQuestion(sqDto);
        sq.setSurvey(survey);
        sqRepository.save(sq);
    }

    @Override
    public SurveyQuestionDto findSurveyQuestionById(long questionId) {
        SurveyQuestion sq = sqRepository.findById(questionId).get();
        return mapToSurveyQuestionDto(sq);
    }

    @Override
    public void updateSurveyQuestion(SurveyQuestionDto question) {
        SurveyQuestion sq = mapToSurveyQuestion(question);
        sqRepository.save(sq);    
    }

    @Override
    public void deleteQuestion(long questionId) {
        sqRepository.deleteById(questionId);
    }

}
