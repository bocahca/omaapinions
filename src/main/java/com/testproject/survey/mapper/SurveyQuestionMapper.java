package com.testproject.survey.mapper;

import com.testproject.survey.dto.SurveyQuestionDto;
import com.testproject.survey.models.SurveyQuestion;

public class SurveyQuestionMapper {

    public static SurveyQuestion mapToSurveyQuestion(SurveyQuestionDto sqDto) {
        return SurveyQuestion.builder()
            .id(sqDto.getId())
            .question(sqDto.getQuestion())
            .survey(sqDto.getSurvey())
            .build();
    }
    public static SurveyQuestionDto mapToSurveyQuestionDto(SurveyQuestion surveyQuestion) {
        return SurveyQuestionDto.builder()
            .id(surveyQuestion.getId())
            .question(surveyQuestion.getQuestion())
            .survey(surveyQuestion.getSurvey())
            .build();
    }

}
