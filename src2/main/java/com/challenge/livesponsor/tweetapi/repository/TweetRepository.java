package com.challenge.livesponsor.tweetapi.repository;

import com.challenge.livesponsor.tweetapi.model.entity.Tweet;
import com.surrealdb.driver.SyncSurrealDriver;
import org.springframework.stereotype.Repository;

@Repository
public class TweetRepository extends GenericRepository<Tweet> implements ITweetRepository {

    public TweetRepository(SyncSurrealDriver driver) {
        super(driver, "tweet");
    }

}
