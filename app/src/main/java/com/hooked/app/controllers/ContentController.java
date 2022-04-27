package com.hooked.app.controllers;

import com.hooked.app.models.Content;
import com.hooked.app.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<Content>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Content> createOne(@RequestBody Content content) {
        return new ResponseEntity<>(repository.save(content), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    Content updateContent(@PathVariable Long id, @RequestBody Content updates) {
        Content status = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getContent() != null) status.setContent(updates.getContent());

        return repository.save(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/customer/{cId}")
    public ResponseEntity<List<Content>> getByCustomerId(@PathVariable Long cId) {
        return new ResponseEntity<>(repository.findAllByCustomer_id(cId), HttpStatus.OK);
    }
}
