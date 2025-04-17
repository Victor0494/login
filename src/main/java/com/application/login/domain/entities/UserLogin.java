package com.application.login.domain.entities;

import com.application.login.domain.valueObject.Roles;
import com.application.login.domain.valueObject.TokenJwt;

import java.util.Set;
import java.util.UUID;

public class UserLogin {

    private UUID id;
    private String username;
    private String password;
    private Set<Roles> role;
    private TokenJwt token;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Roles> getRole() {
        return role;
    }

    public void setRole(Set<Roles> role) {
        this.role = role;
    }

    public TokenJwt getToken() {
        return token;
    }

    public void setToken(TokenJwt token) {
        this.token = token;
    }

//    //TODO
//    public UserLogin(String username, String password) {
//        //Validar as informações do usuario, username e password
//
//        this.username = username;
//        this.password = password;
//    }


    public UserLogin(TokenJwt token) {
        this.token = token;
    }

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
