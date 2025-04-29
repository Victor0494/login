package com.application.login.infra.security;

import com.application.login.infra.persistence.RoleEntity;
import com.application.login.infra.persistence.UserRepository;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.JsonObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final UserRepository userRepository;

    public CustomJwtAuthenticationConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {

        List<String> roles = source.getClaimAsStringList("roles");
        if (roles == null) {
            throw new BadCredentialsException("Roles not found");
        }

        List<RoleEntity> roleEntity = new ArrayList<>(Collections.singletonList(new Gson().fromJson(roles.getFirst(), RoleEntity.class)));

        String userId = source.getSubject();
        if(!userRepository.existsById(userId)) {
            throw new BadCredentialsException("Invalid user");
        }

        Collection<GrantedAuthority> authorities = roleEntity.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()))
                .collect(Collectors.toList());

        JwtGrantedAuthoritiesConverter defaultConverter = new JwtGrantedAuthoritiesConverter();
        authorities.addAll(defaultConverter.convert(source));

        return new JwtAuthenticationToken(source, authorities);
    }
}
