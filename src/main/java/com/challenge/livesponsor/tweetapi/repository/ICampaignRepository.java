package com.challenge.livesponsor.tweetapi.repository;

import com.challenge.livesponsor.tweetapi.model.entity.Campaign;

import java.util.List;

public interface ICampaignRepository {
    List<Campaign> findAll();
    Campaign findOneBy(String column, String value);
    void save(Campaign campaign);
    List<Campaign> update(String id, Campaign entity);
    void delete(String id);
}
