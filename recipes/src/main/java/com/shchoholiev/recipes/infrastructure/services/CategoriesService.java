package com.shchoholiev.recipes.infrastructure.services;

import com.shchoholiev.recipes.application.interfaces.repositories.ICategoriesRepository;
import com.shchoholiev.recipes.application.interfaces.services.ICategoriesService;
import com.shchoholiev.recipes.application.mapping.Mapper;
import com.shchoholiev.recipes.application.models.dtos.CategoryDto;
import com.shchoholiev.recipes.domain.common.PaginationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService implements ICategoriesService {

    @Autowired
    private ICategoriesRepository categoriesRepository;

    private Mapper _mapper = new Mapper();

    @Override
    public void add(CategoryDto categoryDto) {
        var category = _mapper.Map(categoryDto);
        categoriesRepository.add(category);
    }

    @Override
    public CategoryDto getCategory(int id) {
        var category = categoriesRepository.getCategory(id);
        var dto = _mapper.Map(category);
        return dto;
    }

    @Override
    public PaginationWrapper<CategoryDto> getPage(int pageNumber, int pageSize) {
        var categories = categoriesRepository.getPage(pageNumber, pageSize);
        var dtos = _mapper.MapCategories(categories);
        return dtos;
    }

    @Override
    public void update(int id, CategoryDto categoryDto) {
        var category = _mapper.Map(categoryDto);
        categoriesRepository.update(id, category);
    }

    @Override
    public void delete(int id) {
        categoriesRepository.delete(id);
    }
}
