package com.challenge.livesponsor.tweetapi.service;

import com.challenge.livesponsor.tweetapi.configuration.security.JwtService;
import com.challenge.livesponsor.tweetapi.exception.AlreadyExistsException;
import com.challenge.livesponsor.tweetapi.exception.NotFoundException;
import com.challenge.livesponsor.tweetapi.exception.UnauthorizedException;
import com.challenge.livesponsor.tweetapi.model.AuthenticationRequest;
import com.challenge.livesponsor.tweetapi.model.AuthenticationResponse;
import com.challenge.livesponsor.tweetapi.model.RegisterRequest;
import com.challenge.livesponsor.tweetapi.model.user.Role;
import com.challenge.livesponsor.tweetapi.model.user.User;
import com.challenge.livesponsor.tweetapi.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        repository.findOneByEmail(request.getEmail())
                .ifPresent(u -> { throw new AlreadyExistsException("User", request.getEmail());});
        var user = User.builder()
                .name(request.getFirstname() + " " + request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .points(0)
                .role(Role.USER)
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = repository.findOneByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("User", request.getEmail()));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new UnauthorizedException("Password incorrect");
        }

        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
