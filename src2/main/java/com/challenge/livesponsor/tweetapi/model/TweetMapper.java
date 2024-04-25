package com.challenge.livesponsor.tweetapi.model;


import com.challenge.livesponsor.tweetapi.model.dto.TweetDTO;
import com.challenge.livesponsor.tweetapi.model.entity.Tweet;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TweetMapper {
    TweetDTO toDTO(Tweet entity);
    List<TweetDTO> toDTOList(List<Tweet> entity);
    @InheritInverseConfiguration(name = "toDTO")
    Tweet toEntity(TweetDTO dto);
    @InheritInverseConfiguration(name = "toDTOList")
    List<Tweet> toEntityList(List<TweetDTO> model);


}
