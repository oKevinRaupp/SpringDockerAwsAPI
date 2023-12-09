package com.kevinraupp.api.studydockeraws.services;

import com.kevinraupp.api.studydockeraws.entities.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){
        logger.info("Finding one person!");
        Person person = new Person("Kevin","Raupp","Gravataí, Rio Grande do Sul - Brasil","M");
        person.setId(counter.incrementAndGet());
        return person;
    }
    public List<Person> findAll(){
        logger.info("Finding all person!");

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    private Person mockPerson(int i) {
        Person person = new Person("Name: " + i,"LastName: " + i,"Adress - Brasil" + i,"M");
        person.setId(counter.incrementAndGet());
        return person;
    }
    public Person create(Person person) {
        logger.info("Creating one person!");
        return person;
    }
    public Person update(Person person) {
        logger.info("Updating one person!");
        return person;
    }
    public void delete(String id) {
        logger.info("Deleting one person!");
    }
}
