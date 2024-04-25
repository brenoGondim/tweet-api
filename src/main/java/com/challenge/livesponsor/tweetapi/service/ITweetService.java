package com.challenge.livesponsor.tweetapi.service;

import com.challenge.livesponsor.tweetapi.model.tweet.TweetDTO;

import java.util.List;

public interface ITweetService {
    List<TweetDTO> getAll();
    TweetDTO findOneById(Long value);
    void save(TweetDTO tweet, String email);
    TweetDTO update(TweetDTO tweet, Long id);
    void delete(Long id);
}
