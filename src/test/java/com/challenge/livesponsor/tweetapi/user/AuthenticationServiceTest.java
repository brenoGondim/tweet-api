package com.challenge.livesponsor.tweetapi.user;

import com.challenge.livesponsor.tweetapi.security.JwtService;
import com.challenge.livesponsor.tweetapi.exception.AlreadyExistsException;
import com.challenge.livesponsor.tweetapi.security.RegisterRequest;
import com.challenge.livesponsor.tweetapi.security.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.MessageFormat;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    private IUserRepository userRepository;
    private AuthenticationService underTest;
    RegisterRequest requestMock;
    User userMock;

    @BeforeEach
    void setUp() {
        userRepository = mock(IUserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        underTest = new AuthenticationService(
                userRepository,
                passwordEncoder,
                jwtService,
                authenticationManager
        );
    }

    @BeforeEach
    public void setRegisterRequestMock() {
        requestMock = new RegisterRequest(
                "Laura",
                "M",
                "laura@mail.com",
                null
        );
    }

    @BeforeEach
    public void setUserMock() {
        userMock = User.builder()
                .name(requestMock.getFirstname() + " " + requestMock.getLastname())
                .email(requestMock.getEmail())
                .password(null)
                .points(0)
                .role(Role.USER)
                .build();
    }

    @Test
    void canRegister() {
        //given requestMock and userMock

        //when
        underTest.register(requestMock);

        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(userMock);
    }

    @Test
    void willThrownWhenEmailIsTaken() {
        //given requestMock and userMock
        given(userRepository.findOneByEmail(requestMock.getEmail()))
                .willReturn(Optional.ofNullable(userMock));

        //when
        //then
        assertThatThrownBy(() -> underTest.register(requestMock))
                .isInstanceOf(AlreadyExistsException.class)
                .hasMessage(MessageFormat.format("{0} {1} already exists", "User", requestMock.getEmail()));

        verify(userRepository, never()).save(any());
    }

    @Test
    @Disabled
    void authenticate() {
    }
}