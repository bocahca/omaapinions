package com.testproject.survey.service.impl;

import org.springframework.stereotype.Service;

import com.testproject.survey.models.Submission;
import com.testproject.survey.models.UserSurvey;
import com.testproject.survey.repository.SubmissionRepository;
import com.testproject.survey.repository.UserRepository;
import com.testproject.survey.service.SubmissionService;

@Service
public class SubmissionServiceImpl implements SubmissionService  {

    @SuppressWarnings("FieldMayBeFinal")
    private SubmissionRepository submissionRepository;
    @SuppressWarnings("FieldMayBeFinal")
    private UserRepository userRepository;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository, UserRepository userRepository) {
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Submission submission) {
        submissionRepository.save(submission);
    }

    @Override
    public boolean surveyTaken(Long surveyId, String username) {
        
        UserSurvey user = userRepository.findByEmail(username);
        return submissionRepository.existsBySurveyIdAndUser(surveyId, user);
    }
}
