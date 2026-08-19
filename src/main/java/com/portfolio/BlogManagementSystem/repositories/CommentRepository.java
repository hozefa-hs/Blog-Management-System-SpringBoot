package com.portfolio.BlogManagementSystem.repositories;

import com.portfolio.BlogManagementSystem.dtos.CommentResponseDto;
import com.portfolio.BlogManagementSystem.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //find all comments by blog id
    //select * from comment where blogId = ?
    Page<Comment> findAllByBlogId(Long blogId, Pageable pageable);

    //find all comments by user id
    //select * from comment where userId = ?
    Page<Comment> findAllByUserId(Long userId, Pageable pageable);

    Optional<Comment> findByIdAndUserId(Long commentId, Long userId);
}
