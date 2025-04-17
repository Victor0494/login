package com.application.login.infra.controller;

import com.application.login.application.usecases.LoginFromUser;
import com.application.login.domain.entities.UserLogin;
import com.application.login.infra.controller.dto.LoginRequest;
import com.application.login.infra.controller.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class TokenController {

    private final LoginFromUser loginFromUser;

    public TokenController(LoginFromUser loginFromUser) {
        this.loginFromUser = loginFromUser;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        UserLogin login = loginFromUser.login(new UserLogin(loginRequest.userName(), loginRequest.password()));

        return ResponseEntity.ok(new LoginResponse(login.getToken().getAccessToken(), login.getToken().getExpiresIn()));
    }
}
