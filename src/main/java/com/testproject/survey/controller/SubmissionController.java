package com.testproject.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.testproject.survey.dto.SubmissionDto;
import com.testproject.survey.models.UserSurvey;
import com.testproject.survey.security.SecurityUtil;
import com.testproject.survey.service.SubmissionService;
import com.testproject.survey.service.SurveyQuestionService;
import com.testproject.survey.service.SurveyService;

@Controller
public class SubmissionController {
    @SuppressWarnings("FieldMayBeFinal")
    private SubmissionService submissionService;
    @SuppressWarnings("FieldMayBeFinal")
    private SurveyService surveyService;
    @SuppressWarnings("FieldMayBeFinal")
    private SurveyQuestionService sqService;

    public SubmissionController(SurveyQuestionService sqService, SubmissionService submissionService, SurveyService surveyService) {
        this.sqService = sqService;
        this.submissionService = submissionService;
        this.surveyService = surveyService;
    }
   
    

    @PostMapping("/submit")
    public String submitSurvey(@RequestParam Long surveyId, @RequestParam Long questionId, @RequestParam String answer, Model model) {
    UserSurvey currentUser  = SecurityUtil.getSessionUser(); // Implement this method to get the logged-in user

    SubmissionDto submissionDto = new SubmissionDto();
    submissionDto.setUser (currentUser );
    submissionDto.setSurvey(surveyService.findById(surveyId));
    submissionDto.setQuestion(sqService.findQuestionById(questionId)); 
    submissionDto.setAnswer(answer);

    submissionService.saveSubmission(submissionDto);

    return "redirect:/surveys?success"; // Redirect to a success page or survey list
}
}