package com.shchoholiev.recipes.infrastructure.repositories;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shchoholiev.recipes.application.interfaces.repositories.IRecipesRepository;
import com.shchoholiev.recipes.domain.common.PaginationWrapper;
import com.shchoholiev.recipes.domain.entities.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

@Repository
public class RecipesRepository implements IRecipesRepository {

    @Autowired
    private JdbcTemplate _jdbcTemplate;

    private Gson _mapper;

    public RecipesRepository() {
        _mapper = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
    }

    @Override
    public void add(Recipe recipe) {
        var query = "INSERT INTO dbo.Recipes ([Name], Ingredients, [Text], [Thumbnail], [CategoryId]) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "SELECT @@ROWCOUNT";
        var rows = _jdbcTemplate.queryForObject(query,
                new Object[] { recipe.getName(), recipe.getIngredients(), recipe.getText(), recipe.getThumbnail(),
                recipe.getCategory().getId() }, Integer.class);
    }

    @Override
    public Recipe getRecipe(int id) {
        var query = "SELECT \n" +
                "r.[Id],\n" +
                "r.[Name],\n" +
                "r.Ingredients,\n" +
                "r.[Text],\n" +
                "r.Thumbnail,\n" +
                "c.Id AS [Category.Id],\n" +
                "c.[Name] AS [Category.Name]\n" +
                "FROM dbo.Recipes AS r\n" +
                "JOIN dbo.Categories AS c ON c.Id = r.CategoryId\n" +
                "WHERE r.Id = ? " +
                "FOR JSON PATH";

        var json = _jdbcTemplate.queryForObject(query, new Object[] { id }, String.class);
        json = json.substring(1, json.length() - 1);
        var entity = _mapper.fromJson(json, new TypeToken<Recipe>(){});
        return entity;
    }

    @Override
    public PaginationWrapper<Recipe> getPage(int pageNumber, int pageSize) {
        var query = "SELECT\n" +
                "(SELECT\n" +
                    "r.[Id],\n" +
                    "r.[Name],\n" +
                    "r.Thumbnail,\n" +
                    "c.Id AS [Category.Id],\n" +
                    "c.[Name] AS [Category.Name]\n" +
                "FROM dbo.Recipes AS r\n" +
                "JOIN dbo.Categories AS c ON c.Id = r.CategoryId\n" +
                "ORDER BY r.Id\n" +
                "OFFSET ? ROWS\n" +
                "FETCH NEXT ? ROWS ONLY\n" +
                "FOR JSON PATH) AS Items,\n" +
                "COUNT(*) AS TotalCount\n" +
                "FROM dbo.Recipes\n" +
                "FOR JSON PATH\n";

        var json = _jdbcTemplate.queryForObject(query,
                new Object[]{ pageSize * (pageNumber - 1), pageSize}, String.class);
        json = json.substring(1, json.length() - 1);
        var entity = _mapper.fromJson(json, new TypeToken<PaginationWrapper<Recipe>>(){});
        entity.setPagesCount(Math.ceilDiv(entity.getTotalCount(), pageSize));
        return entity;
    }

    @Override
    public PaginationWrapper<Recipe> getPage(int pageNumber, int pageSize, String filter) {
        var query = MessageFormat.format("SELECT\n" +
                        "(SELECT\n" +
                        "r.[Id],\n" +
                        "r.[Name],\n" +
                        "r.Thumbnail,\n" +
                        "c.Id AS [Category.Id],\n" +
                        "c.[Name] AS [Category.Name]\n" +
                        "FROM dbo.Recipes AS r\n" +
                        "JOIN dbo.Categories AS c ON c.Id = r.CategoryId\n" +
                "WHERE r.[Name] LIKE ''%{2}%'' OR Ingredients LIKE ''%{2}%'' OR [Text] LIKE ''%{2}%'' OR c.[Name] LIKE ''%{2}%''\n" +
                "ORDER BY Id\n" +
                "OFFSET {0} ROWS\n" +
                "FETCH NEXT {1} ROWS ONLY\n" +
                "FOR JSON PATH) AS Items,\n" +
                "COUNT(*) AS TotalCount\n" +
                "FROM dbo.Recipes AS r\n" +
                "JOIN dbo.Categories AS c ON c.Id = r.CategoryId " +
                "WHERE r.[Name] LIKE ''%{2}%'' OR Ingredients LIKE ''%{2}%'' OR [Text] LIKE ''%{2}%'' OR c.[Name] LIKE ''%{2}%''\n" +
                "FOR JSON PATH"
                , pageSize * (pageNumber - 1), pageSize, filter);

        try {
            var json = _jdbcTemplate.queryForObject(query, String.class);
            json = json.substring(1, json.length() - 1);
            var entities = _mapper.fromJson(json, new TypeToken<PaginationWrapper<Recipe>>(){});
            entities.setPagesCount(Math.ceilDiv(entities.getTotalCount(), pageSize));
            return entities;
        } catch (Exception e ){
            var a = 1;
        }
        return null;
    }

    @Override
    public PaginationWrapper<Recipe> getPage(int pageNumber, int pageSize, int categoryId) {
        var query = MessageFormat.format("SELECT\n" +
                        "(SELECT\n" +
                        "r.[Id],\n" +
                        "r.[Name],\n" +
                        "r.Thumbnail,\n" +
                        "c.Id AS [Category.Id],\n" +
                        "c.[Name] AS [Category.Name]\n" +
                        "FROM dbo.Recipes AS r\n" +
                        "JOIN dbo.Categories AS c ON c.Id = r.CategoryId\n" +
                        "WHERE CategoryId = {2}\n" +
                        "ORDER BY Id\n" +
                        "OFFSET {0} ROWS\n" +
                        "FETCH NEXT {1} ROWS ONLY\n" +
                        "FOR JSON PATH) AS Items,\n" +
                        "COUNT(*) AS TotalCount\n" +
                        "FROM dbo.Recipes\n" +
                        "WHERE  CategoryId = {2}" +
                        "FOR JSON PATH"
                , pageSize * (pageNumber - 1), pageSize, categoryId);

        var json = _jdbcTemplate.queryForObject(query, String.class);
        json = json.substring(1, json.length() - 1);
        var entities = _mapper.fromJson(json, new TypeToken<PaginationWrapper<Recipe>>(){});
        entities.setPagesCount(Math.ceilDiv(entities.getTotalCount(), pageSize));
        return entities;
    }

    @Override
    public void update(int id, Recipe recipe) {
        var query = "UPDATE dbo.Recipes\n" +
                "SET [Name] = ?, Ingredients = ?, [Text] = ?, [Thumbnail] = ?, [CategoryId] = ?\n" +
                "WHERE Id = ? " +
                "SELECT @@ROWCOUNT";

        try {
            var rows = _jdbcTemplate.queryForObject(query,
                    new Object[] { recipe.getName(), recipe.getIngredients(), recipe.getText(),
                            recipe.getThumbnail(), recipe.getCategory().getId() , id }, Integer.class);
            var b = 2;
        } catch (Exception e ){
            var a = 1;
        }
    }

    @Override
    public void delete(int id) {
        var query = "DELETE FROM dbo.Recipes\n" +
                "WHERE Id = ? " +
                "SELECT @@ROWCOUNT";
        var rows = _jdbcTemplate.queryForObject(query, new Object[] { id }, Integer.class);
    }
}
