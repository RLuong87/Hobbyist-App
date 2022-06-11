package com.hooked.app.controllers;

import com.hooked.app.models.auth.User;
import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.avatar.Avatar;
import com.hooked.app.models.content.Content;
import com.hooked.app.payloads.response.PublicAngler;
import com.hooked.app.payloads.response.SelfAngler;
import com.hooked.app.repositories.AnglerRepository;
import com.hooked.app.repositories.AvatarRepository;
import com.hooked.app.repositories.ContentRepository;
import com.hooked.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class AnglerController {

    @Autowired
    private AnglerRepository anglerRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Angler>> getAllAnglers() {
        return new ResponseEntity<>(anglerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnglerById(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        Angler angler = anglerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new ResponseEntity<>(PublicAngler.build(angler), HttpStatus.OK);
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

    @PostMapping("/content")
    public ResponseEntity<Content> createOne(@RequestBody Content content) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        Angler currentAngler = anglerRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        content.setAngler(currentAngler);

        return new ResponseEntity<>(contentRepository.save(content), HttpStatus.CREATED);
    }

    @PostMapping("/uploadAvatar")
    public ResponseEntity<Avatar> createAvatar(@RequestBody Avatar avatar) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        Angler currentAngler = anglerRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        avatar.setAngler(currentAngler);

        return new ResponseEntity<>(avatarRepository.save(avatar), HttpStatus.CREATED);
    }

    @PutMapping("/updateAvatar")
    public ResponseEntity<Avatar> updateAvatar(@RequestBody Avatar avatar) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        Angler currentAngler = anglerRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        avatar.setAngler(currentAngler);

        return new ResponseEntity<>(avatarRepository.save(avatar), HttpStatus.CREATED);
    }

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
        if (updates.getAvatar() != null) angler.setAvatar(updates.getAvatar());
        if (updates.getContent() != null) angler.setContent(updates.getContent());

        return anglerRepository.save(angler);
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Angler> updateAnglerById(@PathVariable Long id, @RequestBody Angler updates) {
        Angler angler = anglerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) angler.setName(updates.getName());
        if (updates.getStatus() != null) angler.setStatus(updates.getStatus());
        if (updates.getBirthday() != null) angler.setBirthday(updates.getBirthday());
        if (updates.getLocation() != null) angler.setLocation(updates.getLocation());
        if (updates.getAbout() != null) angler.setAbout(updates.getAbout());

        return new ResponseEntity<>(anglerRepository.save(angler), HttpStatus.OK);
    }

    @PutMapping("/content/{id}")
    public @ResponseBody Content updateContent(@PathVariable Long id, @RequestBody Content updates) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        Content content = contentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getTitle() != null) content.setTitle(updates.getTitle());
        if (updates.getContent() != null) content.setContent(updates.getContent());

        return contentRepository.save(content);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        anglerRepository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
