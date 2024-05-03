package com.challenge.livesponsor.tweetapi.user;

import java.util.List;

public interface IUserService {
    List<User> getAll();
    User findOneById(Long value);
    User findOneByEmail(String value);
    User update(User user);
    void delete(String email);
}
