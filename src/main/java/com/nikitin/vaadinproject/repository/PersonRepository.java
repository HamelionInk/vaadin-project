package com.nikitin.vaadinproject.repository;

import java.util.UUID;

import com.nikitin.vaadinproject.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    Person findByUsername(String username);
}
