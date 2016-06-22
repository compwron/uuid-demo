package com.example.controller;

import com.example.model.Baz;
import com.example.model.Foo;
import com.example.repository.BazRepository;
import com.example.repository.FooRepository;
import com.example.resource.BazResource;
import com.example.resource.FooResource;
import com.example.resource.assembler.BazAssembler;
import com.example.resource.assembler.FooAssembler;
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
    private FooAssembler fooAssembler;
    private BazAssembler bazAssembler;

    @Autowired
    public FooController(FooRepository fooRepository, BazRepository bazRepository, FooAssembler fooAssembler, BazAssembler bazAssembler) {
        this.fooRepository = fooRepository;
        this.bazRepository = bazRepository;
        this.fooAssembler = fooAssembler;
        this.bazAssembler = bazAssembler;
    }

    @RequestMapping(value = "/foos", method = RequestMethod.POST)
    public HttpEntity<Object> createFoo(@RequestBody Foo foo) {
        // validation goes here
        Foo savedFoo = fooRepository.save(foo);
        FooResource resource = fooAssembler.toResource(savedFoo);
        return new ResponseEntity(resource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/foos/{uuid}", method = RequestMethod.GET)
    public HttpEntity<Object> getFoo(@PathVariable UUID uuid, HttpServletRequest request) {
        Foo foundFoo = fooRepository.findByUuid(uuid);
        if (foundFoo == null) {
            return new ResponseEntity("", HttpStatus.NOT_FOUND);
        }
        FooResource resource = fooAssembler.toResource(foundFoo);
        return new ResponseEntity(resource, HttpStatus.OK);
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
        BazResource resource = bazAssembler.toResource(savedBaz);
        return new ResponseEntity(resource, HttpStatus.CREATED);
    }
}
