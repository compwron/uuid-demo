package com.example.repository;


import com.example.model.Foo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FooRepository extends CrudRepository<Foo, Long> {
    Foo findByUuid(UUID uuid);
}

