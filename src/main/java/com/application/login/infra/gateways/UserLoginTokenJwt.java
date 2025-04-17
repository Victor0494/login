package com.application.login.infra.gateways;

import com.application.login.application.gateways.UserLoginRepository;
import com.application.login.domain.entities.UserLogin;
import com.application.login.domain.valueObject.TokenJwt;
import com.application.login.infra.persistence.UserEntity;
import com.application.login.infra.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLoginTokenJwt implements UserLoginRepository {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtEncoder jwtEncoder;

    @Override
    public UserLogin login(UserLogin userLogin) {
        Optional<UserEntity> user = userRepository.findByUsername(userLogin.getUsername());

        if(user.isEmpty() || !user.get().isLoginCorrect(userLogin, passwordEncoder)) {
            throw new BadCredentialsException("User or password is invalid");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var claims = JwtClaimsSet.builder()
                .issuer("myBackEnd")
                .subject(user.get().getId().toString())
                .expiresAt(now.plusSeconds(expiresIn))
                .issuedAt(now).build();
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new UserLogin(new TokenJwt(jwtValue, expiresIn));
    }
}
