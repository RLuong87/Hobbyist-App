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
    private CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody Customer getCustomer(@PathVariable Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Customer> postNewCustomer(@RequestBody Customer newCustomer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User currentUser = userRepository.findById(userDetails.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        newCustomer.setUser(currentUser);

        return new ResponseEntity<>(customerRepository.save(newCustomer), HttpStatus.CREATED);
    }

    @PutMapping
    public @ResponseBody ResponseEntity<Customer> updateCustomer(@RequestBody Customer updates) {

        if (updates.getName() != null) updates.setName(updates.getName());
        if (updates.getStatus() != null) updates.setStatus(updates.getStatus());
        if (updates.getBirthday() != null) updates.setBirthday(updates.getBirthday());
        if (updates.getLocation() != null) updates.setLocation(updates.getLocation());
        if (updates.getJobTitle() != null) updates.setJobTitle(updates.getJobTitle());
        if (updates.getEmployer() != null) updates.setEmployer(updates.getEmployer());

        return new ResponseEntity<>(customerRepository.save(updates), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updates) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) customer.setName(updates.getName());
        if (updates.getStatus() != null) customer.setStatus(updates.getStatus());
        if (updates.getBirthday() != null) customer.setBirthday(updates.getBirthday());
        if (updates.getLocation() != null) customer.setLocation(updates.getLocation());
        if (updates.getJobTitle() != null) customer.setJobTitle(updates.getJobTitle());
        if (updates.getEmployer() != null) customer.setEmployer(updates.getEmployer());

        return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
