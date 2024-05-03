package com.challenge.livesponsor.tweetapi.user;

import com.challenge.livesponsor.tweetapi.tweet.TweetDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private Role role;
    private Integer points = 0;
    private List<TweetDTO> tweets;
}
