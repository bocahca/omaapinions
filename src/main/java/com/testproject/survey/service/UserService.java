package com.testproject.survey.service;

import com.testproject.survey.dto.RegistrationDto;
import com.testproject.survey.models.UserSurvey;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserSurvey findByEmail(String email);

    UserSurvey findByUsername(String username);
}
