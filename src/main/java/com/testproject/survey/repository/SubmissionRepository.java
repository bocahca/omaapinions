package com.testproject.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testproject.survey.models.Submission;
import com.testproject.survey.models.UserSurvey;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    boolean existsBySurveyIdAndUser(Long surveyId, UserSurvey user);
}
