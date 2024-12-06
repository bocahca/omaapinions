package com.testproject.survey.service.impl;

import java.util.HashSet;
import java.util.Set;

import com.testproject.survey.dto.RegistrationDto;
import com.testproject.survey.models.UserSurvey;
import com.testproject.survey.models.UserSurvey.RoleEnum;
import com.testproject.survey.repository.UserRepository;
import com.testproject.survey.service.UserService;

public class UserServiceImpl implements UserService {

    @SuppressWarnings("FieldMayBeFinal") // biar gaada muncul warning doang, gapake gpp
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserSurvey user = new UserSurvey();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        Set<RoleEnum> roles = new HashSet<>();
        //default user role adalah USER
        roles.add(RoleEnum.USER);
        user.setRoles(roles);
        
        userRepository.save(user);
    }
    
}
