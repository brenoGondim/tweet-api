package com.challenge.livesponsor.tweetapi.service;

import com.challenge.livesponsor.tweetapi.model.dto.UserDTO;
import com.challenge.livesponsor.tweetapi.model.entity.User;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAll();
    UserDTO findOneById(String value);
    UserDTO findOneByEmail(String value);
    void save(UserDTO user);
    List<UserDTO> update(UserDTO user);
    void delete(String email);
}
