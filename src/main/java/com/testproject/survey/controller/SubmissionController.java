package com.testproject.survey.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.testproject.survey.mapper.SurveyMapper.mapToSurvey;
import com.testproject.survey.models.Submission;
import com.testproject.survey.models.Survey;
import com.testproject.survey.models.SurveyQuestion;
import com.testproject.survey.models.UserSurvey;
import com.testproject.survey.security.SecurityUtil;
import com.testproject.survey.service.SubmissionService;
import com.testproject.survey.service.SurveyService;
import com.testproject.survey.service.UserService;

@Controller
public class SubmissionController {

    @SuppressWarnings("FieldMayBeFinal")
    private SurveyService surveyService;
    @SuppressWarnings("FieldMayBeFinal")
    private UserService userService;
    @SuppressWarnings("FieldMayBeFinal")
    private SubmissionService submissionService; 
    

    public SubmissionController(SurveyService surveyService, UserService userService,
            SubmissionService submissionService) {
        this.surveyService = surveyService;
        this.userService = userService;
        this.submissionService = submissionService;
    }


    @PostMapping("/submitSurvey")
    public String submitSurvey(@RequestParam("survey_id") Long surveyId, @RequestParam Map<String, String> answers) {
    UserSurvey user = new UserSurvey();
    Survey survey = mapToSurvey(surveyService.findSurveyById(surveyId));
    
    String username = SecurityUtil.getSessionUser ();
    if (username != null) {
        user = userService.findByEmail(username); //findByEmai, karena ntah kenapa username pas authentication itu email
    }

    // Save each answer into Submission
    for (Map.Entry<String, String> entry : answers.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();

        if (key.startsWith("answers[") && key.endsWith("]")) {
            String questionIdStr = key.substring(8, key.length() - 1); // ngambil id 
            Long questionId = Long.valueOf(questionIdStr);
            
            // Create Submission object
            Submission submission = new Submission();
            submission.setUser (user);
            submission.setSurvey(survey);
            SurveyQuestion question = new SurveyQuestion();
            question.setId(questionId);
            submission.setQuestion(question);
            submission.setAnswer(value);
            // Save submission
            submissionService.save(submission); 
        } 
    }

    return "redirect:/surveys?submit"; // Redirect after submission
    }
}
