package com.shchoholiev.recipes.application.mapping;

import com.shchoholiev.recipes.application.models.dtos.CategoryDto;
import com.shchoholiev.recipes.application.models.dtos.RecipeDto;
import com.shchoholiev.recipes.domain.entities.Category;
import com.shchoholiev.recipes.domain.entities.Recipe;

public class Mapper {

    public CategoryDto Map(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }

    public Category Map(CategoryDto category) {
        return new Category(category.getId(), category.getName());
    }

    public RecipeDto Map(Recipe recipe) {
        return new RecipeDto(recipe.getId(), recipe.getName(), recipe.getText(), Map(recipe.getCategory()));
    }

    public Recipe Map(RecipeDto recipe) {
        return new Recipe(recipe.getId(), recipe.getName(), recipe.getText(), Map(recipe.getCategory()));
    }
}
