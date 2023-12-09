package com.kevinraupp.api.studydockeraws.services;

import com.kevinraupp.api.studydockeraws.entities.Person;
import org.springframework.stereotype.Service;

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
}
