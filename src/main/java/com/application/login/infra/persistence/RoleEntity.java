package com.application.login.infra.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_roles")
@Getter
@Setter
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    private String name;

    @Getter
    public enum Values {
        BASIC(2L), ADMIN(1L);

        long roleId;

        Values(long roleId) {
            this.roleId = roleId;
        }
    }
}
