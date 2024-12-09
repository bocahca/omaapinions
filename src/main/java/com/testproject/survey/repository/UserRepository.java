    package com.testproject.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

    import com.testproject.survey.models.UserSurvey;

    public interface UserRepository extends JpaRepository<UserSurvey, Long> {
        UserSurvey findByEmail(String email);
        UserSurvey findByUsername(String userName);
        UserSurvey findFirstByUsername(String username);
       
        
    }
