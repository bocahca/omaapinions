package com.testproject.survey.service;

import com.testproject.survey.models.Submission;

public interface SubmissionService {

    void save(Submission submission);
    
    boolean surveyTaken(Long surveyId, String username);
}
