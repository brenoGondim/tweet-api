package com.challenge.livesponsor.tweetapi.tweet;

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
    public ResponseEntity<TweetDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOneById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody TweetDTO tweet) {
        service.save(tweet);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<TweetDTO> update(@RequestBody TweetDTO tweet, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(tweet, id));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<TweetDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
