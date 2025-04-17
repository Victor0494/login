package com.application.login.application.gateways;

import com.application.login.domain.entities.UserLogin;

public interface UserLoginRepository {

    UserLogin login(UserLogin userLogin);
}
