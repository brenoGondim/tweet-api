package com.challenge.livesponsor.tweetapi.service;

import com.challenge.livesponsor.tweetapi.model.dto.CampaignDTO;

import java.util.List;

public interface ICampaignService {
    List<CampaignDTO> getAll();
    CampaignDTO findOneById(String value);
    void save(CampaignDTO campaign);
    List<CampaignDTO> update(CampaignDTO campaign);
    void delete(String id);
}
