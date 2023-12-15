package com.kevinraupp.api.studydockeraws.integrationtests.vo;


import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;


public class PersonVOTest extends RepresentationModel<PersonVOTest> implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

    public PersonVOTest() {
    }

    public PersonVOTest(String firstName, String lastName, String address, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonVOTest person)) return false;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}