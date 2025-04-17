package com.application.login.application.usecases;

import com.application.login.application.gateways.UserLoginGateway;
import com.application.login.domain.entities.login.UserLogin;

public class LoginFromUser {

    private final UserLoginGateway userLogin;

    public LoginFromUser(UserLoginGateway userLogin) {
        this.userLogin = userLogin;
    }

    public UserLogin login(UserLogin login) {
        return userLogin.login(login);
    }
}
