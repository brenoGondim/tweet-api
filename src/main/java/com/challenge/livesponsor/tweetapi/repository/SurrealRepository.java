package com.challenge.livesponsor.tweetapi.repository;

import com.challenge.livesponsor.tweetapi.Human;
import com.surrealdb.driver.SyncSurrealDriver;
import org.springframework.stereotype.Repository;

import java.util.List;

//Transformar essa classe em generico
@Repository
public class SurrealRepository {
    SyncSurrealDriver driver;

    public SurrealRepository(SyncSurrealDriver driver) {
        this.driver = driver;
    }

    public List<Human> findAll() {
        return driver.select("human", Human.class);
    }

    public Human save() {
        Human tobie = driver.create("human", new Human("human:breno1", 251, "jimmy1"));
        return tobie;
    }

}
