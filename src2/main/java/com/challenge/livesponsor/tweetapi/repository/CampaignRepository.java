package com.challenge.livesponsor.tweetapi.repository;

import com.challenge.livesponsor.tweetapi.model.entity.Campaign;
import com.surrealdb.driver.SyncSurrealDriver;
import org.springframework.stereotype.Repository;

@Repository
public class CampaignRepository extends GenericRepository<Campaign> implements ICampaignRepository {

    public CampaignRepository(SyncSurrealDriver driver) {
        super(driver, "campaign");
    }

}
