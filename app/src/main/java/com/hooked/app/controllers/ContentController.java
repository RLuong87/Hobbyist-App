package com.hooked.app.controllers;

import com.hooked.app.models.approve.Approve;
import com.hooked.app.models.content.Content;
import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.auth.User;
import com.hooked.app.payloads.response.SelfContent;
import com.hooked.app.repositories.ApproveRepository;
import com.hooked.app.repositories.ContentRepository;
import com.hooked.app.repositories.AnglerRepository;
import com.hooked.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private AnglerRepository anglerRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    ApproveRepository approveRepository;

    @GetMapping
    public ResponseEntity<Iterable<Content>> getAll() {
        return new ResponseEntity<>(contentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/selfContent")
    public @ResponseBody
    SelfContent getSelfContent(Content content) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
//        Content selfContent = (Content) contentRepository.findByOrderByIdAsc(content.getId());
        Angler currentAngler = anglerRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return SelfContent.build(currentAngler);
    }

    @GetMapping("/customer/{aId}")
    public ResponseEntity<List<Content>> getByAnglerId(@PathVariable Long aId) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }

        return new ResponseEntity<>(contentRepository.findAllByAngler_id(aId), HttpStatus.OK);
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<Content> likeById(@PathVariable Long id, @RequestBody Angler angler) {
        Optional<Content> content = contentRepository.findById(id);

        if (content.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Approve newApproval = new Approve(angler, content.get());
        approveRepository.save(newApproval);
        return new ResponseEntity<>(content.get(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cId}")
    public ResponseEntity<String> deleteContentById(@PathVariable Long cId) {
        contentRepository.deleteById(cId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
