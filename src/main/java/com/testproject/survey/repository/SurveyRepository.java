package com.testproject.survey.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.testproject.survey.models.Survey;

// repository - nge extends JPA Repository biar gausah make querry buat akses CRUD, tapi bisa nambahin custom querry juga

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Optional<Survey> findByCategory(String url);

    @Query("SELECT s from Survey s WHERE s.title LIKE CONCAT('%', :query, '%')")
    List<Survey> searchSurveys(String query);
}
