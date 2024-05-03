package com.challenge.livesponsor.tweetapi.user;

import com.challenge.livesponsor.tweetapi.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final IUserRepository repository;

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User findOneById(Long value) {
        return repository.findById(value).orElse(new User());
    }

    @Override
    public User findOneByEmail(String email) {
        return repository.findOneByEmail(email).orElse(new User());
    }

    @Override
    public User update(User newUser) {
        User oldUser = repository.findOneByEmail(newUser.getEmail()).orElseThrow(() -> new NotFoundException("User", newUser.getEmail()));
        newUser.setId(oldUser.getId());

        return repository.save(newUser);
    }

    @Override
    public void delete(String email) {
        User userEntity = repository.findOneByEmail(email).orElse(new User());
        repository.delete(userEntity);
    }
}
