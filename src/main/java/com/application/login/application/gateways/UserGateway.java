package com.application.login.application.gateways;

import com.application.login.domain.entities.contacts.Contact;

import java.util.List;

public interface UserGateway {

    Contact createContact(Contact contact);

    List<Contact> getContacts();

    Contact updateContact(Contact contact, String userId);

    void deleteContact(String id);
}
