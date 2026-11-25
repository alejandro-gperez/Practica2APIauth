package com.example.practica22.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practica22.entity.Person;
import java.util.Optional;

@Repository("personRepository")
public interface PersonRepository extends JpaRepository<Person, String> {
    Optional<Person> findByApiKey(String apiKey);

}
