package com.kevinraupp.api.studydockeraws.services;

import com.kevinraupp.api.studydockeraws.entities.Person;
import com.kevinraupp.api.studydockeraws.exceptions.ResourceNotFoundException;
import com.kevinraupp.api.studydockeraws.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@Service
public class PersonServices {
    @Autowired
    PersonRepository repository;
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(Long id){
        logger.info("Finding one person!");
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found! Verify!"));
    }
    public List<Person> findAll(){
        logger.info("Finding all person!");
        return repository.findAll();
    }

    public Person create(Person person) {
        logger.info("Creating one person!");
        return repository.save(person);
    }
    public Person update(Person person) {
        logger.info("Updating one person!");

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Id not found! Verify!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }
    public void delete(Long id) {
        logger.info("Deleting one person!");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found! Verify!"));
        repository.delete(entity);
    }
}
