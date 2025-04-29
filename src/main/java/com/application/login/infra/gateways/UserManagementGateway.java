package com.application.login.infra.gateways;

import com.application.login.application.gateways.UserGateway;
import com.application.login.domain.entities.contacts.Contact;
import com.application.login.infra.persistence.ContactEntity;
import com.application.login.infra.persistence.ContactRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public Contact updateContact(Contact contact, String userId) {
        Contact user = contactRepository.findById(userId)
                .map(contactEntity -> new Contact(UUID.fromString(contactEntity.getId()),
                        contactEntity.getName(), contactEntity.getPhone()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.updateContact(contact.getName(), contact.getPhone());

        contactRepository.save(ContactEntity.builder()
                .id(user.getId().toString())
                .name(user.getName())
                .phone(user.getPhone())
                .build());
        return user;
    }

    @Override
    public void deleteContact(String id) {
        contactRepository.deleteById(id);
    }

}
