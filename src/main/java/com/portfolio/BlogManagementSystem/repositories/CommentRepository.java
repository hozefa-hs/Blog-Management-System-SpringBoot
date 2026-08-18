package com.portfolio.BlogManagementSystem.repositories;

import com.portfolio.BlogManagementSystem.dtos.CommentResponseDto;
import com.portfolio.BlogManagementSystem.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBlogId(Long blogId);

    List<Comment> findAllByUserId(Long userId);

    Optional<Comment> findByIdAndUserId(Long commentId, Long userId);
}
