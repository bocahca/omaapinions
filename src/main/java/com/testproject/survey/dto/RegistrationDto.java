package com.testproject.survey.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationDto {
    private long id;
    @NotEmpty(message="username must not be empty")
    private String username;
    @NotEmpty(message = "email should not be empty")
    private String email;
    @NotEmpty(message = "password should not be empty")
    private String password;
}
