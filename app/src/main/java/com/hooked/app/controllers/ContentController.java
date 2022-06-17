package com.hooked.app.controllers;

import com.hooked.app.models.content.Content;
import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.auth.User;
import com.hooked.app.repositories.ContentRepository;
import com.hooked.app.repositories.AnglerRepository;
import com.hooked.app.repositories.UserRepository;
import com.hooked.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<Content>> getAll() {
        return new ResponseEntity<>(contentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/customer/{aId}")
    public ResponseEntity<List<Content>> getByAnglerId(@PathVariable Long aId) {
        return new ResponseEntity<>(contentRepository.findAllByAngler_id(aId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        contentRepository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
