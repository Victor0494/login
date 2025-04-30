package com.application.login.infra.gateways;

import com.application.login.application.gateways.AuthenticatedUserProvider;
import com.application.login.application.gateways.UserGateway;
import com.application.login.domain.entities.contacts.Contact;
import com.application.login.infra.mapper.ContactEntityMapper;
import com.application.login.infra.persistence.ContactEntity;
import com.application.login.infra.persistence.ContactRepository;
import com.application.login.infra.persistence.UserEntity;
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

    private final AuthenticatedUserProvider authenticatedUserProvider;

    private final ContactEntityMapper mapper;

    @Override
    public Contact createContact(Contact contact) {
        ContactEntity contactConverted = mapper.toEntity(contact,
                UserEntity.builder().id(authenticatedUserProvider.getAuthenticatedUserId()).build());
        return mapper.toContact(contactRepository.save(contactConverted));
    }

    @Override
    public List<Contact> getContacts() {
        return contactRepository.findAllByCreatedById(authenticatedUserProvider.getAuthenticatedUserId()).stream()
                .map(mapper::toContact).toList();
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
