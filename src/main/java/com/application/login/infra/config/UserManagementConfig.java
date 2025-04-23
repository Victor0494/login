package com.application.login.infra.config;

import com.application.login.application.gateways.UserGateway;
import com.application.login.application.usecases.UserManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserManagementConfig {

    @Bean
    UserManagement userManagement(UserGateway userGateway) {
        return new UserManagement(userGateway);
    }
}
