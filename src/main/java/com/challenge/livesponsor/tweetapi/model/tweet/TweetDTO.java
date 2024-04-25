package com.challenge.livesponsor.tweetapi.model.tweet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetDTO {
    private String payload;
    private LocalDateTime postDate = LocalDateTime.now();

}
