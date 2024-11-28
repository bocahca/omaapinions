package com.testproject.survey.dto;


import com.testproject.survey.models.Survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyQuestionDto {
    private long id;
    private String question;
    private Survey survey;
}
