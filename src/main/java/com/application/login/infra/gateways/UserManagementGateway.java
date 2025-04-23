package com.application.login.infra.gateways;

import com.application.login.application.gateways.UserGateway;
import com.application.login.domain.entities.contacts.Contact;
import com.application.login.infra.persistence.ContactEntity;
import com.application.login.infra.persistence.ContactRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManagementGateway implements UserGateway {

    private final ContactRepository contactRepository;

    private final ObjectMapper mapper;

    @Override
    public Contact createContact(Contact contact) {
        ContactEntity contactConverted = mapper.convertValue(contact, ContactEntity.class);
        return mapper.convertValue(contactRepository.save(contactConverted), Contact.class);
    }

    @Override
    public List<Contact> getContacts() {
        return contactRepository.findAll().stream()
                .map(entity -> mapper.convertValue(entity, Contact.class)).toList();
    }

    @Override
    public void updateContact(Contact contact) {

    }

    @Override
    public void deleteContact(String id) {
        contactRepository.deleteById(id);
    }
}
