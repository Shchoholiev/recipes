package com.shchoholiev.recipes.application.interfaces.services;

import com.shchoholiev.recipes.application.models.dtos.CategoryDto;

import java.util.List;

public interface ICategoriesService {
    void Add(CategoryDto category);

    CategoryDto GetCategory(int id);

    List<CategoryDto> GetPage(int pageNumber, int pageSize);

    void Update(CategoryDto category);

    void Delete(int id);
}
