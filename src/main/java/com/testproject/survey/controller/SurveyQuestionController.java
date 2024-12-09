package com.testproject.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.testproject.survey.dto.SurveyDto;
import com.testproject.survey.dto.SurveyQuestionDto;
import com.testproject.survey.models.SurveyQuestion;
import com.testproject.survey.service.SurveyQuestionService;
import com.testproject.survey.service.SurveyService;


//controller buat class SurveyQuestion / kelas Question
// routing ditaro di atas method controllernya, biasanya pake @Getmapping atau @PostMapping

@Controller
public class SurveyQuestionController {
    @SuppressWarnings("FieldMayBeFinal")
    private SurveyQuestionService sqService;
    @SuppressWarnings("FieldMayBeFinal")
    private SurveyService surveyService;
    public SurveyQuestionController(SurveyQuestionService sqService, SurveyService surveyService) {
        this.sqService = sqService;
        this.surveyService = surveyService;
    }


    @GetMapping("/questions/new/{surveyId}")
    public String createQuestionForm(@PathVariable("surveyId") long surveyId, Model model) {
        SurveyQuestion sq = new SurveyQuestion();
        SurveyDto survey = surveyService.findSurveyById(surveyId);
        model.addAttribute("survey", survey);
        model.addAttribute("surveyQuestion", sq);
        return "questions-create";
    }

    @GetMapping("/questions/edit/{questionId}")
    public String editQuestion(@PathVariable("questionId") long questionId, Model model) {
        SurveyQuestionDto sq = sqService.findSurveyQuestionById(questionId);
        model.addAttribute("surveyQuestion", sq);
        return "questions-edit";
    }
    @PostMapping("/questions/{surveyId}")
    public String createQuestionForm(@PathVariable("surveyId") Long surveyId,@ModelAttribute("SurveyQuestion") SurveyQuestionDto sqDto, Model model) {
        sqService.createSurveyQuestion(surveyId, sqDto);
        return "redirect:/questions/new/" + surveyId;
    }
    @PostMapping("/questions/edit/{questionId}")
    public String updateQuestion(@PathVariable("questionId") long questionId,  @ModelAttribute("surveyQuestion") SurveyQuestionDto question, Model model) {
        SurveyQuestionDto sqDto = sqService.findSurveyQuestionById(questionId);
        question.setId(questionId);
        question.setSurvey(sqDto.getSurvey());
        sqService.updateSurveyQuestion(question);
        return("redirect:/questions/new/" + sqDto.getSurvey().getId());
    }
    
    @GetMapping("/questions/delete/{questionId}")
    public String deleteQuestion(@PathVariable("questionId")long questionId) {
        long surveyId = sqService.findSurveyQuestionById(questionId).getSurvey().getId();
        sqService.deleteQuestion(questionId);
        return "redirect:/questions/new/" + surveyId;  
    } 
    
    
}
