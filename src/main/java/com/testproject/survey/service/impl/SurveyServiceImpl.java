package com.testproject.survey.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.testproject.survey.dto.SurveyDto;
import static com.testproject.survey.mapper.SurveyMapper.mapToSurvey;
import static com.testproject.survey.mapper.SurveyMapper.mapToSurveyDto;
import com.testproject.survey.models.Survey;
import com.testproject.survey.repository.SurveyRepository;
import com.testproject.survey.service.SurveyService;

//service implementation - implementasi dari interface service yang dibuat

@Service
public class SurveyServiceImpl implements SurveyService {
    @SuppressWarnings("FieldMayBeFinal") // biar gaada muncul warning doang, gapake gpp
    private SurveyRepository surveyRepository;

    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public List<SurveyDto> findAllSurveys() {
        List<Survey> surveys = surveyRepository.findAll();
        return surveys.stream().map((survey) -> mapToSurveyDto(survey)).collect(Collectors.toList());
    }

    @Override
    public Survey saveSurvey(SurveyDto surveyDto) {
        Survey survey = mapToSurvey(surveyDto);
        return surveyRepository.save(survey);
    }

    @Override
    public SurveyDto findSurveyById(long surveyId) {
        Survey survey = surveyRepository.findById(surveyId).get();
        return mapToSurveyDto(survey);
    }

    @Override
    public void updateSurvey(SurveyDto surveyDto) {
        Survey survey = mapToSurvey(surveyDto);
        surveyRepository.save(survey);
    }


    @Override
    public void delete(long surveyId) {
       surveyRepository.deleteById(surveyId);
    }

    @Override
    public List<SurveyDto> searchSurveys(String query) {
        List<Survey> surveys = surveyRepository.searchSurveys(query);
        return surveys.stream().map((survey) -> mapToSurveyDto(survey)).collect(Collectors.toList());
    }
    
}
