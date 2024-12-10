package com.testproject.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testproject.survey.models.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
