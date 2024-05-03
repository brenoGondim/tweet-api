package com.challenge.livesponsor.tweetapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final IUserService service;
    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(mapper.toDTOList(service.getAll()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDTO(service.findOneById(id)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(mapper.toDTO(service.findOneByEmail(email)));
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO user) {
        return ResponseEntity.ok(mapper.toDTO(service.update(mapper.toEntity(user))));
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<UserDTO> delete(@PathVariable String email) {
        service.delete(email);
        return ResponseEntity.ok().build();
    }
}
