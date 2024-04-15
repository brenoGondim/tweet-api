package com.challenge.livesponsor.tweetapi.service;

import com.challenge.livesponsor.tweetapi.model.CampaignMapper;
import com.challenge.livesponsor.tweetapi.model.dto.CampaignDTO;
import com.challenge.livesponsor.tweetapi.model.entity.Campaign;
import com.challenge.livesponsor.tweetapi.repository.ICampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService implements ICampaignService{

    private final ICampaignRepository repository;
    private final CampaignMapper mapper;

    public CampaignService(ICampaignRepository repository, CampaignMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<CampaignDTO> getAll() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    public CampaignDTO findOneById(String value) {
        return mapper.toDTO(repository.findOneBy("id", value));
    }

    @Override
    public void save(CampaignDTO campaign) {
        campaign.setActive(true);
        repository.save(mapper.toEntity(campaign));
    }

    @Override
    public List<CampaignDTO> update(CampaignDTO campaign) {

        Campaign oldCampaignEntity = repository.findOneBy("id", campaign.getId());
        Campaign newCampaignEntity = mapper.toEntity(campaign);
        newCampaignEntity.setId(oldCampaignEntity.getId());

        //TODO: Temporal

        return mapper.toDTOList(repository.update(newCampaignEntity.getId(), newCampaignEntity));
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }
}
