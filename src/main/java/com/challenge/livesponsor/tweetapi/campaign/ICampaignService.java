package com.challenge.livesponsor.tweetapi.campaign;

import java.util.List;

public interface ICampaignService {
    List<CampaignDTO> getAll();
    CampaignDTO findOneById(Long value);
    void save(CampaignDTO campaign);
    CampaignDTO update(CampaignDTO campaign, Long id);
    void delete(Long id);
}
