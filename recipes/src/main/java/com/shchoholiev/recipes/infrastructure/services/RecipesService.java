package com.shchoholiev.recipes.infrastructure.services;

import com.shchoholiev.recipes.application.interfaces.repositories.IRecipesRepository;
import com.shchoholiev.recipes.application.interfaces.services.IRecipesService;
import com.shchoholiev.recipes.application.mapping.Mapper;
import com.shchoholiev.recipes.application.models.dtos.RecipeDto;
import com.shchoholiev.recipes.domain.common.PaginationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipesService implements IRecipesService {

    @Autowired
    private IRecipesRepository _recipesRepository;

    private Mapper _mapper = new Mapper();

    @Override
    public void add(RecipeDto recipeDto) {
        var recipe = _mapper.Map(recipeDto);
        _recipesRepository.add(recipe);
    }

    @Override
    public RecipeDto getRecipe(int id) {
        var recipe = _recipesRepository.getRecipe(id);
        var dto = _mapper.Map(recipe);
        return dto;
    }

    @Override
    public PaginationWrapper<RecipeDto> getPage(int pageNumber, int pageSize) {
        var recipes = _recipesRepository.getPage(pageNumber, pageSize);
        var dtos = _mapper.MapRecipes(recipes);
        return dtos;
    }

    @Override
    public PaginationWrapper<RecipeDto> getPage(int pageNumber, int pageSize, String filter) {
        var recipes = _recipesRepository.getPage(pageNumber, pageSize, filter);
        var dtos = _mapper.MapRecipes(recipes);
        return dtos;
    }

    @Override
    public PaginationWrapper<RecipeDto> getPage(int pageNumber, int pageSize, int categoryId) {
        var recipes = _recipesRepository.getPage(pageNumber, pageSize, categoryId);
        var dtos = _mapper.MapRecipes(recipes);
        return dtos;
    }

    @Override
    public void update(int id, RecipeDto recipeDto) {
        var recipe = _mapper.Map(recipeDto);
        _recipesRepository.update(id, recipe);
    }

    @Override
    public void delete(int id) {
        _recipesRepository.delete(id);
    }
}
