package com.challenge.livesponsor.tweetapi.repository;


import com.challenge.livesponsor.tweetapi.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);

}
