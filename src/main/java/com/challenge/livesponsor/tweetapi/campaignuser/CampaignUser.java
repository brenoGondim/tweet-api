package com.challenge.livesponsor.tweetapi.campaignuser;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "campaign_user")
@IdClass(CampaignUserId.class)
public class CampaignUser {

    @Id
    @Column(name = "code_campaign")
    private Long campaign;

    @Id
    @Column(name = "code_user")
    private Long user;
}
