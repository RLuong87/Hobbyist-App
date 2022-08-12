package com.hooked.app.repositories;

import com.hooked.app.models.avatar.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
