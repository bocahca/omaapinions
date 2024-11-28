package com.testproject.survey.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="survey_questions")
public class SurveyQuestion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String question;

    @ManyToOne
    @JoinColumn(name="survey_id", nullable = false)
    private Survey survey;

}
