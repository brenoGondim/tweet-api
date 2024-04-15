package com.challenge.livesponsor.tweetapi.service;

import com.challenge.livesponsor.tweetapi.model.dto.TweetDTO;

import java.util.List;

public interface ITweetService {
    List<TweetDTO> getAll();
    TweetDTO findOneById(String value);
    void save(TweetDTO tweet);
    List<TweetDTO> update(TweetDTO tweet);
    void delete(String id);
}
