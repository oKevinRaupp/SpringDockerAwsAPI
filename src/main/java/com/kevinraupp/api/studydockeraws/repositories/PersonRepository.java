package com.kevinraupp.api.studydockeraws.repositories;

import com.kevinraupp.api.studydockeraws.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}