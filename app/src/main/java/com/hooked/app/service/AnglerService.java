package com.hooked.app.service;

import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.auth.User;
import com.hooked.app.repositories.AnglerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnglerService {

    @Autowired
    AnglerRepository anglerRepository;

    @Autowired
    UserService userService;

    public Angler saveAngler(Angler angler) {
        return anglerRepository.save(angler);
    }

    public Angler findAngler(Long id) {
        return anglerRepository.findByUser_id(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Angler> findAnglers(String name) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        return anglerRepository.findByName(name);
    }
}
