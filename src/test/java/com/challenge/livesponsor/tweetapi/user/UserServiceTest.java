package com.challenge.livesponsor.tweetapi.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private IUserRepository userRepository;
    private UserService underTest;
    User userMock;

    @BeforeEach
    void setUp() {
        underTest = new UserService(userRepository);
    }

    @BeforeEach
    public void setUserMock() {
        userMock = User.builder()
                .name("Laura M")
                .email("laura@mail.com")
                .password(null)
                .points(0)
                .role(Role.USER)
                .build();
    }

    @Test
    void getAll() {
        //when
        underTest.getAll();
        //then
        verify(userRepository).findAll();
    }

    @Test
    @Disabled
    void findOneById() {
    }

    @Test
    @Disabled
    void findOneByEmail() {
    }

    @Test
    @Disabled
    void update() {
    }

    @Test
    void delete() {
        //given userMock
        given(userRepository.findOneByEmail(userMock.getEmail()))
                .willReturn(Optional.ofNullable(userMock));
        //when
        underTest.delete("laura@mail.com");
        //then
        verify(userRepository).delete(userMock);
    }
}