package com.challenge.livesponsor.tweetapi.controller;

import com.challenge.livesponsor.tweetapi.exception.NotFoundException;
import com.challenge.livesponsor.tweetapi.model.TweetMapper;
import com.challenge.livesponsor.tweetapi.model.dto.TweetDTO;
import com.challenge.livesponsor.tweetapi.service.ITweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<String> save(@RequestBody TweetDTO tweet, @RequestHeader("user-email") String email) {
        try {
            service.save(tweet, email);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<List<TweetDTO>> update(@RequestBody TweetDTO tweet, @PathVariable String id) {
        return ResponseEntity.ok(service.update(tweet, id));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<TweetDTO> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
