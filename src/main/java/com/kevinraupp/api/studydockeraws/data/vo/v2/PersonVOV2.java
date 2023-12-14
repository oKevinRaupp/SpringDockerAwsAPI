package com.kevinraupp.api.studydockeraws.data.vo.v2;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import com.kevinraupp.api.studydockeraws.data.vo.v1.PersonVO;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "firstName", "lastName", "address", "address", "gender", "birthday"})
public class PersonVOV2 extends RepresentationModel<PersonVO> implements Serializable {
    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;
    private Date birthday;

    public PersonVOV2() {
    }

    public PersonVOV2(String firstName, String lastName, String address, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonVOV2 person)) return false;
        return Objects.equals(key, person.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
