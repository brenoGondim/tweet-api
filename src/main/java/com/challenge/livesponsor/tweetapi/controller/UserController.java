package com.challenge.livesponsor.tweetapi.controller;

import com.challenge.livesponsor.tweetapi.exception.AlreadyExistsException;
import com.challenge.livesponsor.tweetapi.exception.DomainRequest;
import com.challenge.livesponsor.tweetapi.model.UserMapper;
import com.challenge.livesponsor.tweetapi.model.dto.UserDTO;
import com.challenge.livesponsor.tweetapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findOneById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.findOneByEmail(email));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody UserDTO user) {
        try {
            service.save(user);
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<List<UserDTO>> update(@RequestBody UserDTO user) {
        return ResponseEntity.ok(service.update(user));
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<UserDTO> delete(@PathVariable String email) {
        service.delete(email);
        return ResponseEntity.ok().build();
    }
}
