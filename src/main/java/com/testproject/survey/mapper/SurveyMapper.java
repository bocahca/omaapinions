package com.testproject.survey.mapper;

import java.util.stream.Collectors;

import com.testproject.survey.dto.SurveyDto;
import static com.testproject.survey.mapper.SurveyQuestionMapper.mapToSurveyQuestionDto;
import com.testproject.survey.models.Survey;

// Mapper - isinya method buat transform dari DTO ke Model asli dan sebaliknya

public class SurveyMapper {

    public static SurveyDto mapToSurveyDto(Survey survey) {
        SurveyDto surveyDto = SurveyDto.builder()
            .id(survey.getId())
            .title(survey.getTitle())
            .category(survey.getCategory())
            .duration(survey.getDuration())
            .questions(survey.getQuestions().stream().map((question)->mapToSurveyQuestionDto(question)).collect(Collectors.toList()))
            .build();
        return surveyDto;
    }
    public static Survey mapToSurvey(SurveyDto survey) {
        Survey surveyDto = Survey.builder()
                        .id(survey.getId())
                        .title(survey.getTitle())
                        .category(survey.getCategory())
                        .duration(survey.getDuration())
                        .build();
        return surveyDto;
    }
}
