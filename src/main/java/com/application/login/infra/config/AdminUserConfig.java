package com.application.login.infra.config;

import com.application.login.infra.persistence.RoleEntity;
import com.application.login.infra.persistence.UserEntity;
import com.application.login.infra.persistence.RoleRepository;
import com.application.login.infra.persistence.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(RoleEntity.Values.ADMIN.name());

        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                userEntity -> {
                    System.out.println("Admin already exist");
                },
                    () -> {
                    UserEntity userEntity = new UserEntity();
                    userEntity.setUsername("admin");
                    userEntity.setPassword(passwordEncoder.encode("123"));
                    userEntity.setRoles(Set.of(roleAdmin));
                    userRepository.save(userEntity);
                    }
        );

    }
}
