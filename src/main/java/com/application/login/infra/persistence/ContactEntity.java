package com.application.login.infra.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String phone;
}
