package com.challenge.livesponsor.tweetapi.controller;

import com.challenge.livesponsor.tweetapi.exception.AlreadyExistsException;
import com.challenge.livesponsor.tweetapi.model.dto.CampaignDTO;
import com.challenge.livesponsor.tweetapi.service.ICampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {
    private final ICampaignService service;

    @Autowired
    public CampaignController(ICampaignService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CampaignDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CampaignDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findOneById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody CampaignDTO campaign) {
        service.save(campaign);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<List<CampaignDTO>> update(@RequestBody CampaignDTO campaign) {
        return ResponseEntity.ok(service.update(campaign));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<CampaignDTO> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
