package com.application.login.application.gateways;

import com.application.login.domain.entities.contacts.Contact;

import java.util.List;

public interface UserGateway {

    Contact createContact(Contact contact);

    List<Contact> getContacts();

    void updateContact(Contact contact);

    void deleteContact(String id);
}
