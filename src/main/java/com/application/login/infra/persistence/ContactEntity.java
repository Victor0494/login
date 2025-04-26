package com.application.login.infra.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String phone;
}
