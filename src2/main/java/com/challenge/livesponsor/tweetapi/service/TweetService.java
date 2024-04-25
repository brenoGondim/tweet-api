package com.challenge.livesponsor.tweetapi.service;

import com.challenge.livesponsor.tweetapi.exception.NotFoundException;
import com.challenge.livesponsor.tweetapi.model.TweetMapper;
import com.challenge.livesponsor.tweetapi.model.dto.TweetDTO;
import com.challenge.livesponsor.tweetapi.model.entity.Campaign;
import com.challenge.livesponsor.tweetapi.model.entity.Tweet;
import com.challenge.livesponsor.tweetapi.model.entity.User;
import com.challenge.livesponsor.tweetapi.repository.ICampaignRepository;
import com.challenge.livesponsor.tweetapi.repository.ITweetRepository;
import com.challenge.livesponsor.tweetapi.repository.IUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetService implements ITweetService{

    private final ITweetRepository repository;
    private final IUserRepository userRepository;
    private final ICampaignRepository campaignRepository;
    private final TweetMapper mapper;

    public TweetService(ITweetRepository repository, IUserRepository userRepository, ICampaignRepository campaignRepository, TweetMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.campaignRepository = campaignRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TweetDTO> getAll() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    public TweetDTO findOneById(String value) {
        return mapper.toDTO(repository.findOneBy("id", value));
    }

    @Override
    public void save(TweetDTO tweet, String email) {
        User user = findUserByEmail(email);
        if (user == null) {
            throw new NotFoundException("User {0} not found", email);
        }
        Tweet tweetEntity = mapper.toEntity(tweet);
        Integer points = getPointsIfSloganMatch(tweet);

        if (points > 0) {
            user.setPoints(user.getPoints() + points);
            userRepository.update(user.getId(), user);
        }

        tweetEntity.setUser(user.getId());
        repository.save(tweetEntity);
    }

    private Integer getPointsIfSloganMatch(TweetDTO tweet) {
        List<Campaign> allCampaignsActive = campaignRepository.findAllActive();
        Boolean tweetContainSlogan = allCampaignsActive.stream()
                .anyMatch(campaign -> tweet.getPayload().contains(campaign.getSlogan()));

        return tweetContainSlogan ? 10 : 0;
    }

    @Override
    public List<TweetDTO> update(TweetDTO tweet, String id) {
        Tweet oldTweetEntity = repository.findOneBy("id", id);
        Tweet newTweetEntity = mapper.toEntity(tweet);
        newTweetEntity.setId(oldTweetEntity.getId());

        BeanUtils.copyProperties(oldTweetEntity, newTweetEntity, "payload");

        //TODO: tratar quando o tweet válido pela campanha for atualizado e não for mais válido e vice-versa (criar tabela campanhas(N) x (N)usuários), usuário.points será o count() da relação

        return mapper.toDTOList(repository.update(id, newTweetEntity));
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    private User findUserByEmail(String email) {
        return userRepository.findOneBy("email", email);
    }

}
