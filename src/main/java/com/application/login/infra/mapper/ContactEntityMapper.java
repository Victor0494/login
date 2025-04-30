package com.application.login.infra.mapper;

import com.application.login.application.gateways.AuthenticatedUserProvider;
import com.application.login.domain.entities.contacts.Contact;
import com.application.login.infra.persistence.ContactEntity;
import com.application.login.infra.persistence.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ContactEntityMapper {

    public ContactEntity toEntity(Contact contact, UserEntity userEntity) {
        return ContactEntity.builder()
                .name(contact.getName())
                .phone(contact.getPhone())
                .createdBy(userEntity)
                .build();
    }

    public Contact toContact(ContactEntity contactEntity) {
        return new Contact(UUID.fromString(contactEntity.getId()),
                                            contactEntity.getName(),
                                            contactEntity.getPhone());
    }
}
