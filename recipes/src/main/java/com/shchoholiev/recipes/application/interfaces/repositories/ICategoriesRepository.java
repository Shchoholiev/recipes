package com.shchoholiev.recipes.application.interfaces.repositories;

import com.shchoholiev.recipes.domain.common.PaginationWrapper;
import com.shchoholiev.recipes.domain.entities.Category;

import java.util.List;

public interface ICategoriesRepository {
    void add(Category category);

    Category getCategory(int id);

    PaginationWrapper<Category> getPage(int pageNumber, int pageSize);

    PaginationWrapper<Category> getPage(int pageNumber, int pageSize, String filter);

    void update(int id, Category category);

    void delete(int id);
}
