package com.shchoholiev.recipes.domain.entities;

import com.shchoholiev.recipes.domain.common.EntityBase;

public class Recipe extends EntityBase {
    private String name;

    private String text;

    private Category category;

    public Recipe(int id, String name, String text, Category category) {
        super(id);
        this.name = name;
        this.text = text;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
