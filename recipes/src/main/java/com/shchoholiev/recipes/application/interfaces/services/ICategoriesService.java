package com.shchoholiev.recipes.application.interfaces.services;

import com.shchoholiev.recipes.application.models.dtos.CategoryDto;
import com.shchoholiev.recipes.domain.common.PaginationWrapper;

import java.util.List;

public interface ICategoriesService {
    void add(CategoryDto category);

    CategoryDto getCategory(int id);

    PaginationWrapper<CategoryDto> getPage(int pageNumber, int pageSize);

    void update(int id, CategoryDto category);

    void delete(int id);
}
