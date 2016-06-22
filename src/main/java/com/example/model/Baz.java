package com.example.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Baz {
    @Id
    private UUID uuid;
    private UUID fooUuid;
    private String asdf;

    public Baz(){
        this.uuid = UUID.randomUUID();
    }
}
