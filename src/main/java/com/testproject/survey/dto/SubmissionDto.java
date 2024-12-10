package com.testproject.survey.dto;

import com.testproject.survey.models.Survey;
import com.testproject.survey.models.SurveyQuestion;
import com.testproject.survey.models.UserSurvey;

import lombok.Data;

@Data
public class SubmissionDto {
    private UserSurvey user;
    private Survey survey;
    private SurveyQuestion question;
    private String answer;

}

