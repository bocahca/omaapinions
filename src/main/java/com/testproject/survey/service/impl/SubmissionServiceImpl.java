package com.testproject.survey.service.impl;

import org.springframework.stereotype.Service;

import com.testproject.survey.models.Submission;
import com.testproject.survey.repository.SubmissionRepository;
import com.testproject.survey.service.SubmissionService;

@Service
public class SubmissionServiceImpl implements SubmissionService  {

    @SuppressWarnings("FieldMayBeFinal")
    private SubmissionRepository submissionRepository;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @Override
    public void save(Submission submission) {
        submissionRepository.save(submission);
    }
}
