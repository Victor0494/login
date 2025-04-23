package com.application.login.application.usecases;

import com.application.login.application.gateways.UserGateway;
import com.application.login.domain.entities.contacts.Contact;

import java.util.List;

public class UserManagement  {

    private final UserGateway userGateway;

    public UserManagement(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public Contact createContact(Contact contact) {
        return userGateway.createContact(contact);
    }

    public List<Contact> getListContacts() {
        return userGateway.getContacts();
    }

    public void updateContact(Contact contact) {
        userGateway.updateContact(contact);
    }

    public void deleteContact(String contactId) {
        userGateway.deleteContact(contactId);
    }
}
