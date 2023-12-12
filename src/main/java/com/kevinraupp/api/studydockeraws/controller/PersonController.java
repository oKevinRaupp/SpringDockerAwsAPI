package com.kevinraupp.api.studydockeraws.controller;


import com.kevinraupp.api.studydockeraws.data.vo.v1.PersonVO;
import com.kevinraupp.api.studydockeraws.data.vo.v2.PersonVOV2;
import com.kevinraupp.api.studydockeraws.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/person/v1")
public class PersonController {
    @Autowired
    private PersonServices services;
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public List<PersonVO> findAll(){
        return services.findAll();
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public PersonVO findByID(@PathVariable(value = "id") Long id){
        return services.findById(id);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public PersonVO create(@RequestBody PersonVO person){
        return services.create(person);
    }

    @PostMapping(value = "/test/v2",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public PersonVOV2 createv2(@RequestBody PersonVOV2 person){
        return services.createV2(person);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public PersonVO update(@RequestBody PersonVO person){
        return services.update(person);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }


}
