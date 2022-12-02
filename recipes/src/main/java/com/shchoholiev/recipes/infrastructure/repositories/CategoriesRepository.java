package com.shchoholiev.recipes.infrastructure.repositories;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shchoholiev.recipes.application.interfaces.repositories.ICategoriesRepository;
import com.shchoholiev.recipes.domain.common.PaginationWrapper;
import com.shchoholiev.recipes.domain.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

@Repository
public class CategoriesRepository implements ICategoriesRepository {

    @Autowired
    private JdbcTemplate _jdbcTemplate;

    private Gson _mapper;

    public CategoriesRepository() {
        _mapper = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
    }

    @Override
    public void add(Category category) {
        var query = "INSERT INTO dbo.Categories ([Name]) " +
                "VALUES (?) " +
                "SELECT @@ROWCOUNT";
        var rows = _jdbcTemplate.queryForObject(query, new Object[] { category.getName() }, Integer.class);
    }

    @Override
    public Category getCategory(int id) {
        var query = MessageFormat.format(
                "SELECT * FROM dbo.Categories WHERE Id = {0}"
                , id);
        var entities = _jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Category.class));
        return entities.get(0);
    }

    @Override
    public PaginationWrapper<Category> getPage(int pageNumber, int pageSize) {
        var query = "SELECT\n" +
                "(SELECT * \n" +
                    "FROM dbo.Categories\n" +
                    "ORDER BY Id\n" +
                    "OFFSET ? ROWS\n" +
                    "FETCH NEXT ? ROWS ONLY\n" +
                "FOR JSON PATH) AS Items,\n" +
                "COUNT(*) AS TotalCount\n" +
                "FROM dbo.Categories\n" +
                "FOR JSON PATH\n";

        var queryResult = _jdbcTemplate.queryForList(query, new Object[]{ pageSize * (pageNumber - 1), pageSize});
        if (!queryResult.isEmpty()) {
            var text = queryResult.get(0).values().stream().findFirst();
            if (text.isPresent()) {
                var json = ((String)text.get());
                json = json.substring(1, json.length() - 1);
                var entities = _mapper.fromJson(json, new TypeToken<PaginationWrapper<Category>>(){});
                return entities;
            }
        }

        return new PaginationWrapper<Category>();
    }

    @Override
    public void update(int id, Category category) {
        var query = "UPDATE dbo.Categories\n" +
                "SET [Name] = ?\n" +
                "WHERE Id = ? " +
                "SELECT @@ROWCOUNT";
        var rows = _jdbcTemplate.queryForObject(query, new Object[] { category.getName(), id }, Integer.class);
    }

    @Override
    public void delete(int id) {
        var query = "DELETE FROM dbo.Categories\n" +
                "WHERE Id = ? " +
                "SELECT @@ROWCOUNT";
        var rows = _jdbcTemplate.queryForObject(query, new Object[] { id }, Integer.class);
    }
}
