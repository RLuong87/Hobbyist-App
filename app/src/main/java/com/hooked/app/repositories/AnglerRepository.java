package com.hooked.app.repositories;

import com.hooked.app.models.angler.Angler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnglerRepository extends JpaRepository<Angler, Long> {
    Optional<Angler> findByUser_id(Long id);


}
