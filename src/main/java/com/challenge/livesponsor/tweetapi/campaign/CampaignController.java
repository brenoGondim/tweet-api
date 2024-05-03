package com.challenge.livesponsor.tweetapi.campaign;

import com.challenge.livesponsor.tweetapi.ApiResponse;
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
    public ResponseEntity<CampaignDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOneById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody CampaignDTO campaign) {
        service.save(campaign);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ApiResponse<CampaignDTO>> update(@RequestBody CampaignDTO campaign, @PathVariable Long id) {
        CampaignDTO updatedCampaign = service.update(campaign, id);
        return ResponseEntity.ok(new ApiResponse<>(updatedCampaign, HttpStatus.OK, "Campaign successfully updated!"));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<CampaignDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
