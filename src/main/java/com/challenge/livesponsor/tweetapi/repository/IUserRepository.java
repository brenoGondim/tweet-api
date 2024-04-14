package com.challenge.livesponsor.tweetapi.repository;

import com.challenge.livesponsor.tweetapi.entity.Human;

import java.util.List;

public interface IHumanRepository {
    List<Human> findAll();
    Human save(Human human);
}
