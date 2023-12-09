package com.kevinraupp.api.studydockeraws.controller;


import com.kevinraupp.api.studydockeraws.entities.Person;
import com.kevinraupp.api.studydockeraws.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
    @Autowired
    private PersonServices services;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll(){
        return services.findAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findByID(@PathVariable(value = "id") String id){
        return services.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person){
        return services.create(person);
    }
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person){
        return services.update(person);
    }
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable(value = "id") String id){
        services.delete(id);
    }

}
