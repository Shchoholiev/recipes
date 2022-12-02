package com.shchoholiev.recipes.application.mapping;

import com.shchoholiev.recipes.application.models.dtos.CategoryDto;
import com.shchoholiev.recipes.application.models.dtos.RecipeDto;
import com.shchoholiev.recipes.domain.common.PaginationWrapper;
import com.shchoholiev.recipes.domain.entities.Category;
import com.shchoholiev.recipes.domain.entities.Recipe;

import java.util.Collections;
import java.util.stream.Collectors;

public class Mapper {

    public CategoryDto Map(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }

    public Category Map(CategoryDto category) {
        return new Category(category.getId(), category.getName());
    }

    public PaginationWrapper<CategoryDto> MapCategories(PaginationWrapper<Category> source) {
        return new PaginationWrapper<CategoryDto>(
                source.getItems() != null ? source.getItems().stream().map(c ->
                        new CategoryDto(c.getId(), c.getName())
                ).collect(Collectors.toList()) : Collections.emptyList(),
                source.getTotalCount()
        );
    }

    public RecipeDto Map(Recipe recipe) {
        return new RecipeDto(recipe.getId(), recipe.getName(), recipe.getIngredients(), recipe.getText(),
                recipe.getThumbnail(), Map(recipe.getCategory()));
    }

    public Recipe Map(RecipeDto recipe) {
        return new Recipe(recipe.getId(), recipe.getName(), recipe.getIngredients(), recipe.getText(),
                recipe.getThumbnail(), Map(recipe.getCategory()));
    }

    public PaginationWrapper<RecipeDto> MapRecipes(PaginationWrapper<Recipe> source) {
        return new PaginationWrapper<RecipeDto>(
                source.getItems().stream().map(s ->
                        new RecipeDto(s.getId(), s.getName(), s.getIngredients(), s.getText(),
                                s.getThumbnail(), Map(s.getCategory()))
                ).collect(Collectors.toList()),
                source.getTotalCount()
        );
    }
}
