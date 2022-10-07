package com.hooked.app.repositories;

import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

//    @Query("SELECT Content FROM Angler ORDER BY local_data_time ASC")
    List<Content> findAllByAngler_id(Long id);

//    List<Content> findByOrderByIdAsc();
//
//    List<Content> findByOrderByIdAsc(Long id);
}
