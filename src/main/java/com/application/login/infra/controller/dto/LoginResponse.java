package com.application.login.infra.controller.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
