package com.challenge.livesponsor.tweetapi.campaignuser;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICampaignUserRepository extends JpaRepository<CampaignUser, Long> {

    int countByUser(Long user);
    List<CampaignUser> findAllByCampaign(Long id);
    CampaignUser findByCampaignAndUser(Long campaign, Long user);
    void deleteAllByCampaign(Long campaign);
}
