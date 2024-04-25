package com.challenge.livesponsor.tweetapi.repository;

import com.challenge.livesponsor.tweetapi.model.entity.Tweet;

import java.util.List;

public interface ITweetRepository {
    List<Tweet> findAll();
    Tweet findOneBy(String column, String value);
    void save(Tweet tweet);
    List<Tweet> update(String id, Tweet entity);
    void delete(String id);
}
