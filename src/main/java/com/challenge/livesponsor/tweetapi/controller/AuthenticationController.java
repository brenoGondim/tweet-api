package com.challenge.livesponsor.tweetapi.controller;

import com.challenge.livesponsor.tweetapi.model.AuthenticationRequest;
import com.challenge.livesponsor.tweetapi.model.AuthenticationResponse;
import com.challenge.livesponsor.tweetapi.model.RegisterRequest;
import com.challenge.livesponsor.tweetapi.model.user.UserDTO;
import com.challenge.livesponsor.tweetapi.service.AuthenticationService;
import com.challenge.livesponsor.tweetapi.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {              //Sign Up
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {    //Login or Sign In
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Retorno Funcionando");
    }
}
