package com.challenge.livesponsor.tweetapi.repository;

import com.challenge.livesponsor.tweetapi.model.campaign.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICampaignRepository extends JpaRepository<Campaign, Long> {
    Campaign findOneBySlogan(String slogan);
    List<Campaign> findAllByActiveTrue();
}
