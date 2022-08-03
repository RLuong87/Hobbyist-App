package com.hooked.app.repositories;

import com.hooked.app.models.angler.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
