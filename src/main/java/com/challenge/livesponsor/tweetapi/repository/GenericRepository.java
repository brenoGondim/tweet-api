package com.challenge.livesponsor.tweetapi.repository;

import com.surrealdb.driver.SyncSurrealDriver;
import com.surrealdb.driver.model.QueryResult;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericRepository<T> {
    SyncSurrealDriver driver;
    String thing;

    public GenericRepository(SyncSurrealDriver driver, String thing) {
        this.driver = driver;
        this.thing = thing;
    }

    public List<T> findAll() {
        var genericClass = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return driver.select(thing, (Class<T>) genericClass);
    }

    public T findOneBy(String column, String value) {
        var genericClass = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        String query = "SELECT * FROM "+ thing +" WHERE "+ column +" = $param";
        Map<String, String> args = new HashMap<>();
        args.put("param", value);

        List<QueryResult<T>> queryResult = driver.query(query, args, (Class<T>) genericClass);
        if (queryResult.get(0).getResult().size() == 0) return null;
        return queryResult.get(0).getResult().get(0);
    }

    public void save(T entity) {
        driver.create(thing, entity);
    }

    public List<T> update(String id, T entity) {
        return driver.update(id, entity);
    }

    public void delete(String id) {
        driver.delete(id);
    }
}
