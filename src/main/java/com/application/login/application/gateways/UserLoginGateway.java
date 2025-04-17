package com.application.login.application.gateways;

import com.application.login.domain.entities.login.UserLogin;

public interface UserLoginGateway {

    UserLogin login(UserLogin userLogin);
}
