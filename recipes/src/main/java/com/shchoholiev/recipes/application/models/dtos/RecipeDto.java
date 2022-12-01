package com.shchoholiev.recipes.application.models.dtos;

public class RecipeDto {

    private int id;

    private String name;

    private String ingredients;

    private String text;

    private String thumbnail;

    private CategoryDto category;

    public RecipeDto(int id, String name, String ingredients, String text, String thumbnail, CategoryDto category) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.text = text;
        this.thumbnail = thumbnail;
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

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
