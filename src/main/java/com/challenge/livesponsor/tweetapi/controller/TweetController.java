package com.challenge.livesponsor.tweetapi.controller;

import com.challenge.livesponsor.tweetapi.exception.AlreadyExistsException;
import com.challenge.livesponsor.tweetapi.model.TweetMapper;
import com.challenge.livesponsor.tweetapi.model.dto.TweetDTO;
import com.challenge.livesponsor.tweetapi.service.ITweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {
    private final ITweetService service;

    @Autowired
    public TweetController(ITweetService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TweetDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TweetDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findOneById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody TweetDTO tweet) {
        service.save(tweet);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<List<TweetDTO>> update(@RequestBody TweetDTO tweet) {
        return ResponseEntity.ok(service.update(tweet));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<TweetDTO> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
