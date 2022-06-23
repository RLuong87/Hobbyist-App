package com.hooked.app.repositories;

import com.hooked.app.models.angler.Angler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnglerRepository extends JpaRepository<Angler, Long> {
    Optional<Angler> findByUser_id(Long id);

//    @Query(value = "SELECT * FROM angler")
//    Optional<Angler> findBy_name();

//    @Query(value = "SELECT * FROM angler WHERE ")
//    List<Angler>

}
