package com.challenge.livesponsor.tweetapi.service;

import com.challenge.livesponsor.tweetapi.model.user.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAll();
    UserDTO findOneById(Long value);
    UserDTO findOneByEmail(String value);
    UserDTO update(UserDTO user);
    void delete(String email);
}
