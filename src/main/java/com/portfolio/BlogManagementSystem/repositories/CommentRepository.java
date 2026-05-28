package com.portfolio.BlogManagementSystem.repositories;

import com.portfolio.BlogManagementSystem.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
