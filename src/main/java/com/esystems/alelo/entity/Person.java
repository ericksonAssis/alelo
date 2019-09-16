package com.esystems.alelo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    public Person() {
        super();
    }

    public Person(long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Person(long id) {
        super();
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
