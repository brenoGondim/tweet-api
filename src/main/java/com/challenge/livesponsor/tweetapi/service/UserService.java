package com.challenge.livesponsor.tweetapi.service;

import com.challenge.livesponsor.tweetapi.exception.NotFoundException;
import com.challenge.livesponsor.tweetapi.model.user.UserMapper;
import com.challenge.livesponsor.tweetapi.model.user.UserDTO;
import com.challenge.livesponsor.tweetapi.model.user.User;
import com.challenge.livesponsor.tweetapi.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    private final IUserRepository repository;
    private final UserMapper mapper;

    public UserService(IUserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDTO> getAll() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    public UserDTO findOneById(Long value) {
        return mapper.toDTO(repository.findById(value).orElse(new User()));
    }

    @Override
    public UserDTO findOneByEmail(String email) {
        return mapper.toDTO(repository.findOneByEmail(email).orElse(new User()));
    }

    @Override
    public UserDTO update(UserDTO user) {
        User oldUser = repository.findOneByEmail(user.getEmail()).orElseThrow(() -> new NotFoundException("User", user.getEmail()));
        User newUser = mapper.toEntity(user);
        newUser.setId(oldUser.getId());

        return mapper.toDTO(repository.save(newUser));
    }

    @Override
    public void delete(String email) {
        User userEntity = repository.findOneByEmail(email).orElse(new User());
        repository.delete(userEntity);
    }
}
