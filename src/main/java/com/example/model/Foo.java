package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Foo {

    public Foo() {
        this.uuid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    private Long id;
    private UUID uuid;
    private String bar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
//        JPA needs the setter but we don't want UUID to be settable
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }
}
