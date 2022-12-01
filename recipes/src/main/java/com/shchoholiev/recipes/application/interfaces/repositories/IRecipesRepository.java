package com.shchoholiev.recipes.application.interfaces.repositories;

import com.shchoholiev.recipes.domain.entities.Category;
import com.shchoholiev.recipes.domain.entities.Recipe;

import java.util.List;

public interface IRecipesRepository {

    void Add(Recipe recipe);

    Recipe GetRecipe(int id);

    List<Recipe> GetPage(int pageNumber, int pageSize);

    void Update(Recipe recipe);

    void Delete(int id);
}
