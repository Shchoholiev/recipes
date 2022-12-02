package com.shchoholiev.recipes.api.controllers;

import com.shchoholiev.recipes.application.interfaces.services.IRecipesService;
import com.shchoholiev.recipes.application.models.dtos.RecipeDto;
import com.shchoholiev.recipes.domain.common.PaginationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/recipes")
public class RecipesController {

    @Autowired
    private IRecipesService _recipesService;

    @GetMapping("{id}")
    public RecipeDto getRecipe(@PathVariable int id){
        return _recipesService.getRecipe(id);
    }

    @GetMapping("page/{pageNumber}")
    public PaginationWrapper<RecipeDto> getRecipesPage(@PathVariable int pageNumber){
        return _recipesService.getPage(pageNumber, 10);
    }

    @GetMapping("page/{pageNumber}/{filter}")
    public PaginationWrapper<RecipeDto> getRecipesPage(@PathVariable int pageNumber, @PathVariable String filter){
        return _recipesService.getPage(pageNumber, 10, filter);
    }

    @GetMapping("page-by-category-id/{pageNumber}/{categoryId}")
    public PaginationWrapper<RecipeDto> getRecipesPage(@PathVariable int pageNumber, @PathVariable int categoryId){
        return _recipesService.getPage(pageNumber, 10, categoryId);
    }

    @PostMapping
    public void addRecipe(@RequestBody RecipeDto categoryDto){
        _recipesService.add(categoryDto);
    }


    @PutMapping("{id}")
    public void updateRecipe(@PathVariable int id, @RequestBody RecipeDto categoryDto){
        _recipesService.update(id, categoryDto);
    }

    @DeleteMapping("{id}")
    public void deleteRecipe(@PathVariable int id){
        _recipesService.delete(id);
    }
}
