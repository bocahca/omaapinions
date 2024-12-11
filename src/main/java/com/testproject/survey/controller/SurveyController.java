package com.testproject.survey.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.testproject.survey.dto.SurveyDto;
import com.testproject.survey.models.Survey;
import com.testproject.survey.models.UserSurvey;
import com.testproject.survey.security.SecurityUtil;
import com.testproject.survey.service.SubmissionService;
import com.testproject.survey.service.SurveyService;
import com.testproject.survey.service.UserService;

import jakarta.validation.Valid;

// controller buat kelas survey
// routing ditaro di atas method controllernya, biasanya pake @Getmapping atau @PostMapping

@Controller
public class SurveyController {
    @SuppressWarnings("FieldMayBeFinal")
    private SurveyService surveyService;
    @SuppressWarnings("FieldMayBeFinal")
    private UserService userService;

    @SuppressWarnings("FieldMayBeFinal")
    private SubmissionService submissionService;

    public SurveyController(SurveyService surveyService, UserService userService, SubmissionService submissionService) {
        this.surveyService = surveyService;
        this.userService = userService;
        this.submissionService = submissionService;
    }
    @GetMapping("")
    public String getOverview() {
        return "overview";
    }
    
    @GetMapping("/surveys")
    public String listSurveys(Model model) {
        UserSurvey user = new UserSurvey();

        List<SurveyDto> surveys = surveyService.findAllSurveys();
        Map<String, List<SurveyDto>> surveysByCategory = surveys.stream()
            .collect(Collectors.groupingBy(SurveyDto::getCategory));
        
        List<String> categories = new ArrayList<>(surveysByCategory.keySet());
        Collections.sort(categories);
        
        String username = SecurityUtil.getSessionUser();
        if(username !=null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("categories", categories);
        model.addAttribute("surveysByCategory", surveysByCategory);
        return "survey-list";
    }

    @GetMapping("/surveys/new")
    public String addSurvey(Model model) {
        model.addAttribute("survey", new Survey());
        return "survey-create";
    }
    @GetMapping("/surveys/search")
    public String searchSurvey(@RequestParam(value = "query") String query, Model model) {
        List<SurveyDto> surveys = surveyService.searchSurveys(query);
        Map<String, List<SurveyDto>> surveysByCategory = surveys.stream()
            .collect(Collectors.groupingBy(SurveyDto::getCategory));
        List<String> categories = new ArrayList<>(surveysByCategory.keySet());
        Collections.sort(categories);
        model.addAttribute("categories", categories);
        model.addAttribute("surveysByCategory", surveysByCategory);
        return "survey-list";

    }
    
    
    @PostMapping("/surveys/new")
    public String saveSurvey(@Valid @ModelAttribute("survey") SurveyDto surveyDto, BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("survey", surveyDto);
            return "survey-create";
        }
        surveyService.saveSurvey(surveyDto);
        return"redirect:/surveys";
    }
    @GetMapping("/surveys/{surveyId}") 
    public String showSurveyQuestions(@PathVariable("surveyId") long surveyId, Model model) {
        
        String username = SecurityUtil.getSessionUser();

        if (submissionService.surveyTaken(surveyId, username)) {
            return "redirect:/surveys?surveyTaken";
        }

        SurveyDto surveyDto = surveyService.findSurveyById(surveyId);
        model.addAttribute("survey", surveyDto);
        return "questions-list";    
    }

    @GetMapping("surveys/edit/{surveyId}")
    public String editSurveys(@PathVariable("surveyId") long surveyId, Model model) {
        SurveyDto survey = surveyService.findSurveyById(surveyId);
        model.addAttribute("survey", survey);
        return "surveys-edit";
    }

    @PostMapping("/surveys/edit/{surveyId}")
    public String updateSurvey(@PathVariable("surveyId") long surveyId, @Valid @ModelAttribute("survey") SurveyDto survey, BindingResult result) {
        if(result.hasErrors()) {
            return "surveys-edit";
        }
        survey.setId(surveyId);
        surveyService.updateSurvey(survey);
        return("redirect:/surveys");
    }
    
    @GetMapping("surveys/delete/{surveyId}")
    public String deleteSurvey(@PathVariable("surveyId") long surveyId) {
        surveyService.delete(surveyId);
        return "redirect:/surveys";
    }
      
    
}
