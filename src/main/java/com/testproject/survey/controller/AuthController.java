package com.testproject.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.testproject.survey.dto.RegistrationDto;
import com.testproject.survey.models.UserSurvey;
import com.testproject.survey.service.UserService;

import jakarta.validation.Valid;



@Controller
public class AuthController {
    @SuppressWarnings("FieldMayBeFinal")
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {
        UserSurvey existingUserEmail = userService.findByEmail(user.getEmail());
        // cek kalo user udah ke register by email
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?failEmail";
        }
        
        UserSurvey existingUsername = userService.findByUsername(user.getUsername());

        //cek kalo username udh dipake
        if (existingUsername != null && existingUsername.getUsername() != null && !existingUsername.getUsername().isEmpty()) {
            return "redirect:/register?failUsername";
        }
        //check validation
        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);

        return "redirect:/surveys?success";
    }
    
    @GetMapping("/login")
    public String loginPage() { 
        return "login";
    }
    

}
