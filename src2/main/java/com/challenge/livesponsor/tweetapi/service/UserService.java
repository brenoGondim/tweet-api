package com.challenge.livesponsor.tweetapi.service;

import com.challenge.livesponsor.tweetapi.model.UserMapper;
import com.challenge.livesponsor.tweetapi.model.dto.UserDTO;
import com.challenge.livesponsor.tweetapi.model.entity.User;
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
    public UserDTO findOneById(String value) {
        return mapper.toDTO(repository.findOneBy("id", value));
    }

    @Override
    public UserDTO findOneByEmail(String value) {
        return mapper.toDTO(findOneEntityByEmail(value));
    }

    @Override
    public void save(UserDTO user) {
        User userEntity = findOneEntityByEmail(user.getEmail());

        if (userEntity != null) {
            throw new AlreadyExistsException();
        }
        repository.save(mapper.toEntity(user));
    }

    @Override
    public List<UserDTO> update(UserDTO user) {
        User oldUserEntity = findOneEntityByEmail(user.getEmail());
        User newUserEntity = mapper.toEntity(user);
        newUserEntity.setId(oldUserEntity.getId());

        return mapper.toDTOList(repository.update(newUserEntity.getId(), newUserEntity));
    }

    @Override
    public void delete(String email) {
        User userEntity = findOneEntityByEmail(email);
        repository.delete(userEntity.getId());
    }

    private User findOneEntityByEmail(String email) {
        return repository.findOneBy("email", email);
    }
}
