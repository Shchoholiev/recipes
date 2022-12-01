package com.shchoholiev.recipes.application.interfaces.services;

import com.shchoholiev.recipes.application.models.dtos.RecipeDto;

import java.util.List;

public interface IRecipesRepository {

    void Add(RecipeDto recipe);

    RecipeDto GetRecipe(int id);

    List<RecipeDto> GetPage(int pageNumber, int pageSize);

    void Update(RecipeDto recipe);

    void Delete(int id);
}
