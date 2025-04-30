package com.application.login.infra.persistence;

import com.application.login.domain.entities.login.UserLogin;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String id;

    @Column(unique = true)
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roleEntities;

    public boolean isLoginCorrect(UserLogin userLogin, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(userLogin.getPassword(), this.password);
    }
}