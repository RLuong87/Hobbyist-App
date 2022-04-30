package com.hooked.app.controllers;

import com.hooked.app.models.auth.User;
import com.hooked.app.models.Customer;
import com.hooked.app.repositories.CustomerRepository;
import com.hooked.app.repositories.UserRepository;
import com.hooked.app.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository repository;

    @GetMapping
    public @ResponseBody ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody Customer getCustomer(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Customer> postNewCustomer(@RequestBody Customer newCustomer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User currentUser = userRepository.findById(userDetails.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        newCustomer.setUser(currentUser);

        return new ResponseEntity<>(repository.save(newCustomer), HttpStatus.CREATED);
    }

//    @PostMapping
//    public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer) {
//        return new ResponseEntity<>(repository.save(newCustomer), HttpStatus.CREATED);
//    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updates) {
        Customer customer = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) customer.setName(updates.getName());
        if (updates.getProfile() != null) customer.setProfile(updates.getProfile());
        if (updates.getContent() != null) customer.setContent(updates.getContent());

        return new ResponseEntity<>(repository.save(customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
