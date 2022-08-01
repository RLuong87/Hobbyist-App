package com.hooked.app.repositories;

import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findAllByAngler_id(Long id);

}
