package com.application.login.infra.config;

import com.application.login.application.gateways.UserLoginGateway;
import com.application.login.application.usecases.LoginFromUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserLoginConfig {

    @Bean
    LoginFromUser login(UserLoginGateway repository) {
        return new LoginFromUser(repository);
    }
}
