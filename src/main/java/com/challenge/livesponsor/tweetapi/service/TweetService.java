package com.challenge.livesponsor.tweetapi.service;

import com.challenge.livesponsor.tweetapi.model.TweetMapper;
import com.challenge.livesponsor.tweetapi.model.dto.TweetDTO;
import com.challenge.livesponsor.tweetapi.model.entity.Tweet;
import com.challenge.livesponsor.tweetapi.repository.ITweetRepository;
import com.challenge.livesponsor.tweetapi.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService implements ITweetService{

    private final ITweetRepository repository;
    private final IUserRepository userRepository;
    private final TweetMapper mapper;

    public TweetService(ITweetRepository repository, IUserRepository userRepository, TweetMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
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
    public void save(TweetDTO tweet) {
        Tweet tweetEntity = mapper.toEntity(tweet);

        tweetEntity.setPostDateNow();
        tweetEntity.setUser(tweetEntity.getUser());
        repository.save(tweetEntity);
    }
    //TODO: ajustar save e update
    @Override
    public List<TweetDTO> update(TweetDTO tweet) {
        //Tweet oldTweetEntity = findOneEntityByEmail(tweet.getEmail());
        Tweet newTweetEntity = mapper.toEntity(tweet);
        //newTweetEntity.setId(oldTweetEntity.getId());

        return mapper.toDTOList(repository.update(newTweetEntity.getId(), newTweetEntity));
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

}
