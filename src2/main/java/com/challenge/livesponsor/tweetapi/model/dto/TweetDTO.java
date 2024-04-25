package com.challenge.livesponsor.tweetapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class TweetDTO {
    private String id;
    private String payload;
    private LocalDateTime postDate = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }
}