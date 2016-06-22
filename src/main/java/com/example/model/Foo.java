package com.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Foo {

    @Id
    private UUID uuid;
    private String bar;

    public Foo() {
        this.uuid = UUID.randomUUID();
    }

    public void setUuid(UUID uuid) {
//      Override lombok setter
//      not externally settable via API
    }
}
