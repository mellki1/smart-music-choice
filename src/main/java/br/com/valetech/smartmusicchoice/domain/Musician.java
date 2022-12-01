package br.com.valetech.smartmusicchoice.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import java.util.UUID;

public class Musician {
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
