package com.example.repository;

import com.example.model.Baz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BazRepository extends CrudRepository<Baz, Long> {
}

