package com.shchoholiev.recipes.domain.common;

import jakarta.persistence.Entity;

@Entity
public abstract class EntityBase {

    private int id;

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
