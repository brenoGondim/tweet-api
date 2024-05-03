package com.challenge.livesponsor.tweetapi.campaignuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignUserId implements Serializable {
    private Long campaign;
    private Long user;
}
