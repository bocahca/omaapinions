package com.testproject.survey.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.testproject.survey.models.UserSurvey;
import com.testproject.survey.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @SuppressWarnings("FieldMayBeFinal")
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSurvey user = userRepository.findFirstByUsername(username);

        if(user !=null) {
            User authUser = new User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())) 
                .collect(Collectors.toList())
            );
            return authUser;
        }else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    
}
