package com.example;

import com.example.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleFooApi {
    public static void main(String[] args) {
        SpringApplication.run(ExampleFooApi.class, args);
    }

    @Autowired
    private FooRepository fooRepository;
}
