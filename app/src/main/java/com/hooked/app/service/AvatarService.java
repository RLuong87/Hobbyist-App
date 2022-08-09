package com.hooked.app.service;

import com.hooked.app.models.avatar.Avatar;
import com.hooked.app.repositories.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvatarService {

    @Autowired
    private AvatarRepository repository;

    public void createAvatar(Avatar avatar) {
        repository.save(avatar);
    }

    public List<Avatar> getAllAvatar() {
        return repository.findAll();
    }

    public Optional<Avatar> findAvatarById(Long id) {
        return repository.findById(id);
    }
}
