package com.hooked.app.controllers;

import com.hooked.app.models.UserStatus;
import com.hooked.app.repositories.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/status")
public class UserStatusController {

    @Autowired
    private UserStatusRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<UserStatus>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserStatus> createOne(@RequestBody UserStatus status) {
        System.out.println(status.getCustomer().getId());

        return new ResponseEntity<>(repository.save(status), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public @ResponseBody UserStatus updateStatus(@PathVariable Long id, @RequestBody UserStatus updates) {
        UserStatus status = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getStatus() != null) status.setStatus(updates.getStatus());

        return repository.save(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/customer/{cId}")
    public ResponseEntity<List<UserStatus>> getByCustomerId(@PathVariable Long cId) {
        return new ResponseEntity<>(repository.findAllByCustomer_id(cId), HttpStatus.OK);
    }
}
