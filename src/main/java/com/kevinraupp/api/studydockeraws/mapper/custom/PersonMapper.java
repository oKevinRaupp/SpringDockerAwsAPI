package com.kevinraupp.api.studydockeraws.mapper.custom;

import com.kevinraupp.api.studydockeraws.data.model.Person;
import com.kevinraupp.api.studydockeraws.data.vo.v2.PersonVOV2;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {
    public PersonVOV2 convertEntityToVo(Person person){
        PersonVOV2 vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        vo.setBirthday(new Date());
        return vo;
    }
    public Person convertVoToEntity(PersonVOV2 person){
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
//        entity.setBirthday(new Date());
        return entity;
    }
}
