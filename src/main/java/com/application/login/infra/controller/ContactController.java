package com.application.login.infra.controller;

import com.application.login.application.usecases.UserManagement;
import com.application.login.domain.entities.contacts.Contact;
import com.application.login.infra.controller.dto.ContactRequestDto;
import com.application.login.infra.controller.dto.ContactResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/contact")
public class ContactController {

    private final UserManagement userManagement;

    public ContactController(UserManagement userManagement) {
        this.userManagement = userManagement;
    }

    @PostMapping
    public ResponseEntity<ContactResponseDto> createContact(@RequestBody ContactRequestDto requestDto) {
        Contact response = userManagement.createContact(new Contact(requestDto.name(), requestDto.phone()));

        URI location = URI.create("/contact/" + response.getId());

        return ResponseEntity.created(location)
                .body(new ContactResponseDto(response.getId().toString(), response.getName(), response.getPhone()));
    }

    @GetMapping
    public ResponseEntity<List<ContactResponseDto>> getListOfContacts() {
        List<Contact> listContacts = userManagement.getListContacts();

        return ResponseEntity.ok(listContacts.stream()
                .map(contact -> new ContactResponseDto(contact.getId().toString(), contact.getName(), contact.getPhone())).toList());
    }

    @PutMapping("/{userId}")
    private ResponseEntity<ContactResponseDto> updateContact(@RequestBody ContactRequestDto requestDto,
                                                             @PathVariable("userId") String userId) {
        Contact response = userManagement.updateContact(new Contact(requestDto.name(), requestDto.phone()), userId);

        return ResponseEntity
                .ok(new ContactResponseDto(response.getId().toString(), response.getName(), response.getPhone()));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteContact(@PathVariable String userId) {
        userManagement.deleteContact(userId);

        return ResponseEntity.noContent().build();
    }
}