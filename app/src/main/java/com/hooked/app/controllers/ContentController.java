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
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnglerRepository anglerRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<Content>> getAll() {
        return new ResponseEntity<>(contentRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Content> createOne(@RequestBody Content content) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        Angler currentAngler = anglerRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        content.setAngler(currentAngler);

        return new ResponseEntity<>(contentRepository.save(content), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public @ResponseBody Content updateContent(@PathVariable Long id, @RequestBody Content updates) {
        Content content = contentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getContent() != null) content.setContent(updates.getContent());

        return contentRepository.save(content);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        contentRepository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/customer/{cId}")
    public ResponseEntity<List<Content>> getByAnglerId(@PathVariable Long aId) {
        return new ResponseEntity<>(contentRepository.findAllByAngler_id(aId), HttpStatus.OK);
    }
}
