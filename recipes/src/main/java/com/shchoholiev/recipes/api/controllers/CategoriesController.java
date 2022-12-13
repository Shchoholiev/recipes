package com.shchoholiev.recipes.api.controllers;

import com.shchoholiev.recipes.application.interfaces.services.ICategoriesService;
import com.shchoholiev.recipes.application.models.dtos.CategoryDto;
import com.shchoholiev.recipes.domain.common.PaginationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/categories")
public class CategoriesController {

    @Autowired
    private ICategoriesService _categoriesService;

    @GetMapping("{id}")
    public CategoryDto getCategory(@PathVariable int id){
        return _categoriesService.getCategory(id);
    }

    @GetMapping("page/{pageNumber}")
    public PaginationWrapper<CategoryDto> getCategoriesPage(@PathVariable int pageNumber){
        return _categoriesService.getPage(pageNumber, 10);
    }

    @GetMapping("page/{pageNumber}/{filter}")
    public PaginationWrapper<CategoryDto> getCategoriesPage(@PathVariable int pageNumber, @PathVariable String filter){
        return _categoriesService.getPage(pageNumber, 10, filter);
    }

    @PostMapping
    public void addCategory(@RequestBody CategoryDto categoryDto){
        _categoriesService.add(categoryDto);
    }


    @PutMapping("{id}")
    public void updateCategory(@PathVariable int id, @RequestBody CategoryDto categoryDto){
        _categoriesService.update(id, categoryDto);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable int id){
        _categoriesService.delete(id);
    }
}
