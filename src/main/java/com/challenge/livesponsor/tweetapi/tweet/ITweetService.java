package com.challenge.livesponsor.tweetapi.tweet;

import java.util.List;

public interface ITweetService {
    List<TweetDTO> getAll();
    TweetDTO findOneById(Long value);
    void save(TweetDTO tweet);
    TweetDTO update(TweetDTO tweet, Long id);
    void delete(Long id);
}
