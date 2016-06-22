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

    private final FooRepository fooRepository;
    private final BazRepository bazRepository;

    @Autowired
    public FooController(FooRepository fooRepository, BazRepository bazRepository) {
        this.fooRepository = fooRepository;
        this.bazRepository = bazRepository;
    }

    @RequestMapping(value = "/foos", method = RequestMethod.POST)
    public HttpEntity<Object> createFoo(@RequestBody Foo foo) {
        // validation goes here
        Foo savedFoo = fooRepository.save(foo);
        return new ResponseEntity(savedFoo, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/foos/{uuid}", method = RequestMethod.GET)
    public HttpEntity<Object> getFoo(@PathVariable UUID uuid, HttpServletRequest request) {
        Foo foundFoo = fooRepository.findByUuid(uuid);
        if (foundFoo == null) {
            return new ResponseEntity("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(foundFoo, HttpStatus.OK);
    }

    @RequestMapping(value = "/foos", method = RequestMethod.GET)
    public Iterable<Foo> getFoos() {
        return fooRepository.findAll();
    }

    @RequestMapping(value = "/foos/{uuid}/bazzes", method = RequestMethod.POST)
    public ResponseEntity<Object> createBaz(@PathVariable UUID uuid, @RequestBody Baz baz) {
        if(fooRepository.findByUuid(uuid) == null){
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        baz.setFooUuid(uuid);
        Baz savedBaz = bazRepository.save(baz);
        return new ResponseEntity(savedBaz, HttpStatus.CREATED);
    }
}
