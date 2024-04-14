package com.challenge.livesponsor.tweetapi.repository;

import com.challenge.livesponsor.tweetapi.entity.Human;
import com.surrealdb.driver.SyncSurrealDriver;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

//Transformar essa classe em generico

public class SurrealRepository<T>{
    SyncSurrealDriver driver;

    public SurrealRepository(SyncSurrealDriver driver) {
        this.driver = driver;
    }

    public List<T> findAll() {
        var genericClass = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return driver.select("human", (Class<T>) genericClass);
    }

    public Human save() {
        Human tobie = driver.create("human", new Human("human:breno1", 251, "jimmy1"));
        return tobie;
    }

}
