package com.shchoholiev.recipes.domain.entities;

import com.shchoholiev.recipes.domain.common.EntityBase;

public class Category extends EntityBase {
    public String name;

    public Category(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
