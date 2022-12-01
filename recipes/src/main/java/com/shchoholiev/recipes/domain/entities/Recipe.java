package com.shchoholiev.recipes.domain.entities;

import com.shchoholiev.recipes.domain.common.EntityBase;

public class Recipe extends EntityBase {

    private String name;

    private String ingredients;

    private String text;

    private String thumbnail;

    private Category category;

    public Recipe(int id, String name, String text, String ingredients, String thumbnail, Category category) {
        super(id);
        this.name = name;
        this.text = text;
        this.ingredients = ingredients;
        this.thumbnail = thumbnail;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
