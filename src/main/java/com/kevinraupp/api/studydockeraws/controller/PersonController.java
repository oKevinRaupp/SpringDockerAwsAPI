package com.kevinraupp.api.studydockeraws.controller;

import com.kevinraupp.api.studydockeraws.converters.NumberConverter;
import com.kevinraupp.api.studydockeraws.entities.Person;
import com.kevinraupp.api.studydockeraws.exceptions.UnsuportedMathOperationException;
import com.kevinraupp.api.studydockeraws.math.SimpleMath;
import com.kevinraupp.api.studydockeraws.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
    @Autowired
    private PersonServices services;
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findByID(@PathVariable(value = "id") String id){
        return services.findById(id);
    }
}
