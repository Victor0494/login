package com.application.login.infra.gateways;

import com.application.login.application.gateways.AuthenticatedUserProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserProviderImpl implements AuthenticatedUserProvider {

    @Override
    public String getAuthenticatedUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getSubject();
        }
        throw new IllegalStateException("Usuário não autenticado ou token inválido.");
    }
}
