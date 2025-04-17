package com.application.login.infra.gateways;

import com.application.login.application.gateways.UserGateway;
import com.application.login.domain.entities.contacts.Contact;
import org.springframework.stereotype.Service;

@Service
public class UserManagementGateway implements UserGateway {

    @Override
    public Contact createContact(Contact contact) {
        return null;
    }

    @Override
    public void updateContact(Contact contact) {

    }

    @Override
    public void deleteContact(String id) {

    }
}
