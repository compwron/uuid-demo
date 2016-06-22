package com.example.unit.controller;

import com.example.controller.FooController;
import com.example.model.Baz;
import com.example.repository.FooRepository;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class FooControllerTest {

    @Test
    public void creatingBazForMissingFooReturnsNotFound() {
        FooRepository fakeFooRepository = mock(FooRepository.class);
        FooController fooController = new FooController(fakeFooRepository, null, null, null);
        UUID nonexistentFoo = UUID.randomUUID();
        ResponseEntity<Object> responseEntity = fooController.createBaz(nonexistentFoo, new Baz());
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.UNPROCESSABLE_ENTITY));
        assertThat(responseEntity.getBody(), is(nullValue()));
    }
}
