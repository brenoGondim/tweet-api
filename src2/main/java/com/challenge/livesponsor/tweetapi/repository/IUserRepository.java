package com.challenge.livesponsor.tweetapi.repository;

import com.challenge.livesponsor.tweetapi.model.dto.UserDTO;
import com.challenge.livesponsor.tweetapi.model.entity.User;

import java.util.List;

public interface IUserRepository {
    List<User> findAll();
    User findOneBy(String column, String value);
    void save(User user);
    List<User> update(String id, User entity);
    void delete(String id);
}
