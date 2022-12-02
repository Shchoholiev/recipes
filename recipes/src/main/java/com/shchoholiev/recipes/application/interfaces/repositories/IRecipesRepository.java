package com.shchoholiev.recipes.application.interfaces.repositories;

import com.shchoholiev.recipes.domain.common.PaginationWrapper;
import com.shchoholiev.recipes.domain.entities.Recipe;

import java.util.List;

public interface IRecipesRepository {

    void add(Recipe recipe);

    Recipe getRecipe(int id);

    PaginationWrapper<Recipe> getPage(int pageNumber, int pageSize);

    void update(int id, Recipe recipe);

    void delete(int id);
}
