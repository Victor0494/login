package com.application.login.application.gateways;

import com.application.login.domain.entities.contacts.Contact;

public interface UserGateway {

    Contact createContact(Contact contact);

    void updateContact(Contact contact);

    void deleteContact(String id);
}
