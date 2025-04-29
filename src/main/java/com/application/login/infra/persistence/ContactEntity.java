package com.application.login.infra.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
