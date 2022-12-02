package com.shchoholiev.recipes.application.interfaces.services;

import com.shchoholiev.recipes.application.models.dtos.RecipeDto;
import com.shchoholiev.recipes.domain.common.PaginationWrapper;

import java.util.List;

public interface IRecipesService {

    void add(RecipeDto recipe);

    RecipeDto getRecipe(int id);

    PaginationWrapper<RecipeDto> getPage(int pageNumber, int pageSize);

    void update(int id, RecipeDto recipe);

    void delete(int id);
}
