package com.kevinraupp.api.studydockeraws.services;

import com.kevinraupp.api.studydockeraws.controller.PersonController;
import com.kevinraupp.api.studydockeraws.data.vo.v1.PersonVO;
import com.kevinraupp.api.studydockeraws.data.vo.v2.PersonVOV2;
import com.kevinraupp.api.studydockeraws.exceptions.RequiredObjectIsNullException;
import com.kevinraupp.api.studydockeraws.exceptions.ResourceNotFoundException;
import com.kevinraupp.api.studydockeraws.mapper.DozerMapper;
import com.kevinraupp.api.studydockeraws.mapper.custom.PersonMapper;
import com.kevinraupp.api.studydockeraws.model.Person;
import com.kevinraupp.api.studydockeraws.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {
    @Autowired
    PersonRepository repository;
    @Autowired
    PersonMapper mapper;
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found! Verify! "));
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findByID(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all person!");
        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findByID(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO create(PersonVO person) {
        if (person == null) {
            logger.info("Trying to create a null object!");
            throw new RequiredObjectIsNullException();
        }
        logger.info("Creating one person!");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findByID(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        if (person == null) {
            logger.info("[V2] Trying to create a null object! [V2]");
            throw new RequiredObjectIsNullException();
        }
        logger.info("[V2] Creating one person! [V2]");

        var entity = mapper.convertVoToPerson(person);
        var vo = mapper.convertPersonToVo(repository.save(entity));
        vo.add(linkTo(methodOn(PersonController.class).findByID(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) {
        if (person == null) {
            logger.info("Trying to update a null object!");
            throw new RequiredObjectIsNullException();
        }
        logger.info("Updating one person!");

        var entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("Id not found! Verify!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findByID(vo.getKey())).withSelfRel());
        return vo;
    }

    @Transactional
    public PersonVO disablePerson(Long id) {
        logger.info("Disabling one person!");
        repository.disablePerson(id);
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found! Verify! "));
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findByID(id)).withSelfRel());
        return vo;
    }

    @Transactional
    public PersonVO enablePerson(Long id) {
        logger.info("Enabling one person!");
        repository.enablePerson(id);
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found! Verify! "));
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findByID(id)).withSelfRel());
        return vo;
    }


    public void delete(Long id) {
        logger.info("Deleting one person!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found! Verify!"));
        repository.delete(entity);
    }
}