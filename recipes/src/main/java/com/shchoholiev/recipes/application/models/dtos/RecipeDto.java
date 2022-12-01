package com.shchoholiev.recipes.application.models.dtos;

public class RecipeDto {

    private int id;

    private String name;

    private String text;

    private CategoryDto category;

    public RecipeDto(int id, String name, String text, CategoryDto category) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
