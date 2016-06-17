package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Baz {
    @Id
    @GeneratedValue
    private Long id;

    public Long getFooId() {
        return fooId;
    }

    public void setFooId(Long fooId) {
        this.fooId = fooId;
    }

    private Long fooId;
    private String asdf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsdf() {
        return asdf;
    }

    public void setAsdf(String asdf) {
        this.asdf = asdf;
    }
}
