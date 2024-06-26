package com.challenge.livesponsor.tweetapi.campaign;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CampaignMapper {
    CampaignDTO toDTO(Campaign entity);
    List<CampaignDTO> toDTOList(List<Campaign> entity);
    @InheritInverseConfiguration(name = "toDTO")
    Campaign toEntity(CampaignDTO dto);
    @InheritInverseConfiguration(name = "toDTOList")
    List<Campaign> toEntityList(List<CampaignDTO> model);


}
