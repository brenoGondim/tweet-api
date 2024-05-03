package com.challenge.livesponsor.tweetapi.tweet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ITweetRepository extends JpaRepository<Tweet, Long> {
    @Query("SELECT t FROM Tweet t WHERE t.postDate > :initialDate")
    List<Tweet> findAllTweetsAfterDate(LocalDateTime initialDate);
}
