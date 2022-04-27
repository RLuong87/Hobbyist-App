package com.hooked.app.repositories;

import com.hooked.app.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findAllByCustomer_id(Long id);
}
