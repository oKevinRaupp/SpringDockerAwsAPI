package com.kevinraupp.api.studydockeraws.integrationtests.vo;

import java.io.Serializable;
import java.util.Objects;

public class AccountCredentialsVOTest implements Serializable {

    private String username;
    private String password;

    public AccountCredentialsVOTest() {
    }

    public AccountCredentialsVOTest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountCredentialsVOTest that)) return false;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}