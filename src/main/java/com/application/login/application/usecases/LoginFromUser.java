package com.application.login.application.usecases;

import com.application.login.application.gateways.UserLoginRepository;
import com.application.login.domain.entities.UserLogin;

public class LoginFromUser {

    private final UserLoginRepository userLogin;

    public LoginFromUser(UserLoginRepository userLogin) {
        this.userLogin = userLogin;
    }

    public UserLogin login(UserLogin login) {
        return userLogin.login(login);
    }
}
