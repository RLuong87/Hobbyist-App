package com.hooked.app.controllers;

import com.hooked.app.models.angler.Comment;
import com.hooked.app.models.auth.User;
import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.avatar.Avatar;
import com.hooked.app.models.content.Content;
import com.hooked.app.payloads.response.PublicAngler;
import com.hooked.app.payloads.response.SelfAngler;
import com.hooked.app.repositories.AnglerRepository;
import com.hooked.app.repositories.AvatarRepository;
import com.hooked.app.repositories.CommentRepository;
import com.hooked.app.repositories.ContentRepository;
import com.hooked.app.service.AnglerService;
import com.hooked.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class AnglerController {

    //TODO: Create a method to allow users to like content, their own and their friends
    //TODO: Must have: friending system, request, accept, block ---> Nice to have: follow a user
    //TODO: Create a method to allow a user to comment on content, their own and their friends
    //TODO: Implement Google Maps API to allow users to shar their location
    //TODO: Give users a way to check ocean tides

    @Autowired
    private AnglerRepository anglerRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private UserService userService;

    @Autowired
    AnglerService anglerService;

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

    @GetMapping("/avatar/{id}")
    public ResponseEntity<?> getAvatarById(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        return new ResponseEntity<>(avatarRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/self")
    public @ResponseBody
    SelfAngler getSelf() {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        Angler currentAngler = anglerRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return SelfAngler.build(currentAngler);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Angler>> findAnglerByName(@PathVariable String name) {
        return ResponseEntity.ok(anglerService.findAnglers(name));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Angler>> findAnglerByLocation(@PathVariable String location) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        return new ResponseEntity<>(anglerRepository.findByLocation(location), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Angler>> findAnglerByStatus(@PathVariable String status) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        return new ResponseEntity<>(anglerRepository.findByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/getAllAvatars")
    public ResponseEntity<List<Avatar>> getAllAvatars() {
        return new ResponseEntity<>(avatarRepository.findAll(), HttpStatus.OK);
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

    @PostMapping("/comment")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        Angler currentAngler = anglerRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        comment.setAngler(currentAngler);

        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.CREATED);
    }

//    @PostMapping("/uploadAvatar")
//    public ResponseEntity<Avatar> createAvatar(@RequestBody Avatar avatar) {
//        User currentUser = userService.getCurrentUser();
//
//        if (currentUser == null) {
//            return null;
//        }
//        Angler currentAngler = anglerRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        avatar.setAngler(currentAngler);
//
//        return new ResponseEntity<>(avatarRepository.save(avatar), HttpStatus.CREATED);
//    }

//    @PutMapping("/updateAvatar")
//    public ResponseEntity<Avatar> updateAvatar(@RequestBody Avatar avatar) {
//        User currentUser = userService.getCurrentUser();
//
//        if (currentUser == null) {
//            return null;
//        }
//        Angler currentAngler = anglerRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        avatar.setAngler(currentAngler);
//
//        return new ResponseEntity<>(avatarRepository.save(avatar), HttpStatus.CREATED);
//    }

    @PutMapping
    public @ResponseBody Angler updateAngler(@RequestBody Angler updates) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        Angler angler = anglerRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getAvatar() != null) angler.setAvatar(updates.getAvatar());
        if (updates.getName() != null) angler.setName(updates.getName());
        if (updates.getStatus() != null) angler.setStatus(updates.getStatus());
        if (updates.getBirthday() != null) angler.setBirthday(updates.getBirthday());
        if (updates.getLocation() != null) angler.setLocation(updates.getLocation());
        if (updates.getAbout() != null) angler.setAbout(updates.getAbout());

        return anglerRepository.save(angler);
    }

    @PutMapping("/content/{id}")
    public @ResponseBody
    Content updateContent(@PathVariable Long id, @RequestBody Content updates) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        Content content = contentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getPicture() != null) content.setPicture(updates.getPicture());
        if (updates.getContent() != null) content.setContent(updates.getContent());

        return contentRepository.save(content);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        anglerRepository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
