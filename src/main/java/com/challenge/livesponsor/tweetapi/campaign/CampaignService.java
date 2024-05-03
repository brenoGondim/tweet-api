package com.challenge.livesponsor.tweetapi.campaign;

import com.challenge.livesponsor.tweetapi.exception.NotFoundException;
import com.challenge.livesponsor.tweetapi.campaignuser.CampaignUser;
import com.challenge.livesponsor.tweetapi.tweet.Tweet;
import com.challenge.livesponsor.tweetapi.user.User;
import com.challenge.livesponsor.tweetapi.campaignuser.ICampaignUserRepository;
import com.challenge.livesponsor.tweetapi.tweet.ITweetRepository;
import com.challenge.livesponsor.tweetapi.user.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService implements ICampaignService{

    private final ICampaignRepository repository;
    private final ITweetRepository tweetRepository;
    private final ICampaignUserRepository campaignUserRepository;
    private final IUserRepository userRepository;
    private final CampaignMapper mapper;


    public CampaignService(ICampaignRepository repository, ITweetRepository tweetRepository, CampaignMapper mapper, ICampaignUserRepository campaignUserRepository, IUserRepository userRepository) {
        this.repository = repository;
        this.tweetRepository = tweetRepository;
        this.mapper = mapper;
        this.campaignUserRepository = campaignUserRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CampaignDTO> getAll() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    public CampaignDTO findOneById(Long id) {
        return mapper.toDTO(repository.findById(id).orElse(new Campaign()));
    }

    @Override
    public void save(CampaignDTO campaign) {
        repository.save(mapper.toEntity(campaign));
    }

    @Override
    @Transactional
    public CampaignDTO update(CampaignDTO campaign, Long id) {
        Campaign oldCampaign = repository.findById(id).orElseThrow(() -> new NotFoundException("Campaign with id", id.toString()));
        Campaign newCampaign = mapper.toEntity(campaign);
        BeanUtils.copyProperties(oldCampaign, newCampaign, "slogan");

        handleCampaignPoints(newCampaign);
        //TODO: Temporal
        return mapper.toDTO(repository.save(newCampaign));
    }

    private void handleCampaignPoints(Campaign newCampaign) {
        campaignUserRepository.deleteAllByCampaign(newCampaign.getId());
        List<Tweet> tweetsAfterCampaign = tweetRepository.findAllTweetsAfterDate(newCampaign.getInitialDate());

        tweetsAfterCampaign.forEach(tweet -> {
            if (tweet.getPayload().contains(newCampaign.getSlogan())) {
                CampaignUser userAlreadyInCampaign = campaignUserRepository.findByCampaignAndUser(newCampaign.getId(), tweet.getUser());
                if (userAlreadyInCampaign == null) {
                    campaignUserRepository.save(new CampaignUser(newCampaign.getId(), tweet.getUser()));
                }
            }
            User user = userRepository.findById(tweet.getUser()).orElseThrow(() -> new NotFoundException("User with id", tweet.getUser().toString()));
            userUpdatePoints(user);
        });
    }
    public void userUpdatePoints(User user) {
        int countCampaignByUser = campaignUserRepository.countByUser(user.getId());
        user.setPoints(countCampaignByUser * 10);
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        Campaign campaignEntity = repository.findById(id).orElse(new Campaign());
        repository.delete(campaignEntity);
    }
}
