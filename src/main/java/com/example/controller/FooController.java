package com.example.controller;

import com.example.model.Baz;
import com.example.model.Foo;
import com.example.repository.BazRepository;
import com.example.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
public class FooController {
    @Autowired
    private FooRepository fooRepository;
    @Autowired
    private BazRepository bazRepository;

    @RequestMapping(value = "/foos", method = RequestMethod.POST)
    public HttpEntity<Object> createFoo(@RequestBody Foo foo) {
        // validation goes here
        Foo savedFoo = fooRepository.save(foo);
        return new ResponseEntity(savedFoo, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/foos/{uuid}", method = RequestMethod.GET)
    public HttpEntity<Object> getFoo(@PathVariable UUID uuid, HttpServletRequest request) {
        Foo foundFoo = fooRepository.findByUuid(uuid);
        if (foundFoo == null){
            return new ResponseEntity("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(foundFoo, HttpStatus.OK);
    }

    @RequestMapping(value = "/foos", method = RequestMethod.GET)
    public Iterable<Foo> getFoos() {
        return fooRepository.findAll();
    }

    @RequestMapping(value = "/foos/{uuid}/bazzes", method = RequestMethod.POST)
    public HttpEntity<Object> createPaymentMethod(@PathVariable UUID uuid, @RequestBody Baz baz) {
        // Validation goes here
        baz.setFooId(fooRepository.findByUuid(uuid).getId());
        Baz savedBaz = bazRepository.save(baz);
        return new ResponseEntity(savedBaz, HttpStatus.CREATED);
    }
}
