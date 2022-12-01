package com.shchoholiev.recipes.application.interfaces.repositories;

import com.shchoholiev.recipes.domain.entities.Category;

import java.util.List;

public interface ICategoriesRepository {
    void Add(Category category);

    Category GetCategory(int id);

    List<Category> GetPage(int pageNumber, int pageSize);

    void Update(Category category);

    void Delete(int id);
}
