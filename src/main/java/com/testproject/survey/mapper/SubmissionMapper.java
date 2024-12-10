package com.testproject.survey.mapper;

import com.testproject.survey.dto.SubmissionDto;
import com.testproject.survey.models.Submission;

public class SubmissionMapper {
    public static Submission mapToSubmission(SubmissionDto submissionDto) {
        return Submission.builder()
            .user(submissionDto.getUser ())
            .survey(submissionDto.getSurvey())
            .question(submissionDto.getQuestion())
            .answer(submissionDto.getAnswer())
            .build();
    }
}