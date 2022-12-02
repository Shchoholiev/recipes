package com.shchoholiev.recipes.domain.common;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//@Entity
public abstract class EntityBase {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public EntityBase() { }

    public EntityBase(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
