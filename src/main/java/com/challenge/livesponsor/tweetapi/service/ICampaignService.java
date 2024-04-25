package com.challenge.livesponsor.tweetapi.service;

import com.challenge.livesponsor.tweetapi.model.campaign.CampaignDTO;

import java.util.List;

public interface ICampaignService {
    List<CampaignDTO> getAll();
    CampaignDTO findOneById(Long value);
    void save(CampaignDTO campaign);
    CampaignDTO update(CampaignDTO campaign, Long id);
    void delete(Long id);
}
