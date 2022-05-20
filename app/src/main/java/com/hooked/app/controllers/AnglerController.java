package com.hooked.app.controllers;

import com.hooked.app.models.auth.User;
import com.hooked.app.models.angler.Angler;
import com.hooked.app.payloads.response.SelfAngler;
import com.hooked.app.repositories.AnglerRepository;
import com.hooked.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class AnglerController {

    @Autowired
    private AnglerRepository anglerRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Angler>> getAllAnglers() {
        return new ResponseEntity<>(anglerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Angler getCustomer(@PathVariable Long id) {
        return anglerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<SelfAngler> createAngler(@RequestBody Angler newAngler) {

        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        newAngler.setUser(currentUser);

        Angler angler = anglerRepository.save(newAngler);

        return new ResponseEntity<>(SelfAngler.build(angler), HttpStatus.CREATED);
    }

//    @PutMapping
//    public @ResponseBody ResponseEntity<Angler> updateAngler(@RequestBody Angler updates) {
//
//        if (updates.getName() != null) updates.setName(updates.getName());
//        if (updates.getStatus() != null) updates.setStatus(updates.getStatus());
//        if (updates.getBirthday() != null) updates.setBirthday(updates.getBirthday());
//        if (updates.getLocation() != null) updates.setLocation(updates.getLocation());
//        if (updates.getAbout() != null) updates.setAbout(updates.getAbout());
//
//        return new ResponseEntity<>(anglerRepository.save(updates), HttpStatus.OK);
//    }

    @PutMapping
    public @ResponseBody Angler updateAngler(@RequestBody Angler updates) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        Angler angler = anglerRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) angler.setName(updates.getName());
        if (updates.getStatus() != null) angler.setStatus(updates.getStatus());
        if (updates.getBirthday() != null) angler.setBirthday(updates.getBirthday());
        if (updates.getLocation() != null) angler.setLocation(updates.getLocation());
        if (updates.getAbout() != null) angler.setAbout(updates.getAbout());

        return anglerRepository.save(angler);
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Angler> updateAnglerById(@PathVariable Long id, @RequestBody Angler updates) {
        Angler angler = anglerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) angler.setName(updates.getName());
        if (updates.getStatus() != null) angler.setStatus(updates.getStatus());
        if (updates.getBirthday() != null) angler.setBirthday(updates.getBirthday());
        if (updates.getLocation() != null) angler.setLocation(updates.getLocation());
        if (updates.getAbout() != null) updates.setAbout(updates.getAbout());

        return new ResponseEntity<>(anglerRepository.save(angler), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        anglerRepository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
