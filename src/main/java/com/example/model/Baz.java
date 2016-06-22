package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Baz {
    @Id
    private UUID uuid;
    private UUID fooUuid;
    private String asdf;

    public Baz(){
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getFooUuid() {
        return fooUuid;
    }

    public void setFooUuid(UUID fooUuid) {
        this.fooUuid = fooUuid;
    }

    public String getAsdf() {
        return asdf;
    }

    public void setAsdf(String asdf) {
        this.asdf = asdf;
    }
}
