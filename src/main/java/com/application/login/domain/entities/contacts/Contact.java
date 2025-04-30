package com.application.login.domain.entities.contacts;

import com.application.login.domain.entities.login.UserLogin;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

public class Contact {

    private static final String NAME_REGEX = "^[A-Za-zÀ-ÖØ-öø-ÿ ]{2,100}$";
    private static final String PHONE_REGEX = "^(\\(\\d{2}\\)\\s?)?9?\\d{4}-?\\d{4}$";

    private UUID id;
    private String name;
    private String phone;
    private UserLogin createdBy;

    public Contact(String name, String phone) {
//        if (!name.matches(NAME_REGEX)) {
//            throw new IllegalArgumentException("Nome inválido.");
//        }
//        if (!phone.matches(PHONE_REGEX)) {
//            throw new IllegalArgumentException("Telefone inválido.");
//        }

        this.name = name;
        this.phone = phone;
    }

    public void updateContact(String newName, String newPhone) {
        if(!ObjectUtils.isEmpty(newName) && !this.name.equals(newName)) {
            this.name = newName;
        }

        if(!ObjectUtils.isEmpty(newPhone) && !this.phone.equals(newPhone)) {
            this.phone = newPhone;
        }
    }

    public Contact(UUID id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Contact() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public UserLogin getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserLogin createdBy) {
        this.createdBy = createdBy;
    }
}
