package com.challenge.livesponsor.tweetapi.model.user;

import com.challenge.livesponsor.tweetapi.model.tweet.TweetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String name;
    private Integer points = 0;
    private List<TweetDTO> tweets;
}
