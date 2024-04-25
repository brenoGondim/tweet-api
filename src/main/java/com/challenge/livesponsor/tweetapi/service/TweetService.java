package com.challenge.livesponsor.tweetapi.service;

import com.challenge.livesponsor.tweetapi.exception.NotFoundException;
import com.challenge.livesponsor.tweetapi.model.tweet.TweetMapper;
import com.challenge.livesponsor.tweetapi.model.tweet.TweetDTO;
import com.challenge.livesponsor.tweetapi.model.campaign.Campaign;
import com.challenge.livesponsor.tweetapi.model.campaignuser.CampaignUser;
import com.challenge.livesponsor.tweetapi.model.tweet.Tweet;
import com.challenge.livesponsor.tweetapi.model.user.User;
import com.challenge.livesponsor.tweetapi.repository.ICampaignRepository;
import com.challenge.livesponsor.tweetapi.repository.ICampaignUserRepository;
import com.challenge.livesponsor.tweetapi.repository.ITweetRepository;
import com.challenge.livesponsor.tweetapi.repository.IUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService implements ITweetService{

    private final ITweetRepository repository;
    private final IUserRepository userRepository;
    private final ICampaignRepository campaignRepository;
    private final ICampaignUserRepository campaignUserRepository;
    private final TweetMapper mapper;

    public TweetService(ITweetRepository repository, IUserRepository userRepository, ICampaignRepository campaignRepository, ICampaignUserRepository campaignUserRepository, TweetMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.campaignRepository = campaignRepository;
        this.campaignUserRepository = campaignUserRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TweetDTO> getAll() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    public TweetDTO findOneById(Long id) {
        return mapper.toDTO(repository.findById(id).orElse(new Tweet()));
    }

    @Override
    public void save(TweetDTO tweet, String email) {
        User user = userRepository.findOneByEmail(email).orElseThrow(() -> new NotFoundException("User", email));
        Tweet tweetEntity = mapper.toEntity(tweet);
        tweetEntity.setUser(user.getId());

        handleCampaignPoints(tweetEntity, user);

        repository.save(tweetEntity);
    }

    @Override
    public TweetDTO update(TweetDTO tweet, Long id) {
        Tweet oldTweet = repository.findById(id).orElseThrow(() -> new NotFoundException("Tweet", id.toString()));
        Tweet newTweet = mapper.toEntity(tweet);
        BeanUtils.copyProperties(oldTweet, newTweet, "payload");
        User user = userRepository.findById(newTweet.getUser()).orElseThrow(() -> new NotFoundException("User with id", newTweet.getUser().toString()));

        handleCampaignPoints(newTweet, user);

        return mapper.toDTO(repository.save(newTweet));
    }

    private void handleCampaignPoints(Tweet tweet, User user) {
        List<Campaign> allCampaigns = campaignRepository.findAllByActiveTrue();

        allCampaigns.forEach(campaign -> {
            CampaignUser userInCampaign = campaignUserRepository.findByCampaignAndUser(campaign.getId(), user.getId());

            if (tweet.getPayload().contains(campaign.getSlogan())) {
                if (userInCampaign == null)
                    campaignUserRepository.save(new CampaignUser(campaign.getId(), user.getId()));
            } else {
                if (userInCampaign != null) {
                    campaignUserRepository.delete(userInCampaign);
                }
            }

        });
        userUpdatePoints(user);
    }
    public void userUpdatePoints(User user) {
        int countCampaignByUser = campaignUserRepository.countByUser(user.getId());
        user.setPoints(countCampaignByUser * 10);
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        Tweet tweetEntity = repository.findById(id).orElse(new Tweet());
        repository.delete(tweetEntity);
    }


}
