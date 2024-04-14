package com.challenge.livesponsor.tweetapi.controller;

import com.challenge.livesponsor.tweetapi.Human;
import com.challenge.livesponsor.tweetapi.repository.SurrealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final SurrealRepository surrealRepository;

    @Autowired
    public UserController(SurrealRepository surrealRepository) {
        this.surrealRepository = surrealRepository;
    }

    @GetMapping
    public ResponseEntity<List<Human>> getAll() {
        return ResponseEntity.ok(surrealRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Human> save() {
        return ResponseEntity.ok(surrealRepository.save());
    }


}
