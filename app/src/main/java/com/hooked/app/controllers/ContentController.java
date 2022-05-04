package com.hooked.app.controllers;

import com.hooked.app.models.Content;
import com.hooked.app.models.Customer;
import com.hooked.app.models.auth.User;
import com.hooked.app.repositories.ContentRepository;
import com.hooked.app.repositories.CustomerRepository;
import com.hooked.app.repositories.UserRepository;
import com.hooked.app.security.service.UserDetailsImpl;
import com.hooked.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private CustomerRepository customerRepository;

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
        Customer currentCustomer = customerRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        content.setCustomer(currentCustomer);

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
    public ResponseEntity<List<Content>> getByCustomerId(@PathVariable Long cId) {
        return new ResponseEntity<>(contentRepository.findAllByCustomer_id(cId), HttpStatus.OK);
    }
}
