package com.application.login.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, String> {

    List<ContactEntity> findAllByCreatedById(String userId);
}
