package com.kevinraupp.api.studydockeraws;

import com.kevinraupp.api.studydockeraws.security.jwt.GenerateToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class StudyDockerAwsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyDockerAwsApplication.class, args);
        GenerateToken.generateToken();
    }
}