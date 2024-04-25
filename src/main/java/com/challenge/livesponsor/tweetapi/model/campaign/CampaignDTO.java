package com.challenge.livesponsor.tweetapi.model.campaign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDTO {

    private String slogan;
    private Boolean active = true;
    private LocalDateTime initialDate = LocalDateTime.now();

}
