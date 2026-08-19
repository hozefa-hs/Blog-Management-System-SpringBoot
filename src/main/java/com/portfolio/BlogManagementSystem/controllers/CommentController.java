package com.portfolio.BlogManagementSystem.controllers;

import com.portfolio.BlogManagementSystem.dtos.CommentResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateCommentDto;
import com.portfolio.BlogManagementSystem.dtos.UpdateCommentDto;
import com.portfolio.BlogManagementSystem.entities.User;
import com.portfolio.BlogManagementSystem.services.BlogService;
import com.portfolio.BlogManagementSystem.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create-comment/{blogId}")
    public ResponseEntity<CommentResponseDto> createComment(
            @Valid @RequestBody CreateCommentDto createCommentDto,
            @PathVariable Long blogId,
            @AuthenticationPrincipal User user) {

        CommentResponseDto commentResponseDto = commentService.createComment(createCommentDto, blogId, user.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDto);
    }


    @GetMapping("/blogs/{blogId}/comments")
    public ResponseEntity<Page<CommentResponseDto>> getAllCommentsOfBlog(
            @PathVariable Long blogId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<CommentResponseDto> allCommentsByBlogId = commentService.getAllCommentsByBlogId(blogId, page, size);
        return ResponseEntity.ok(allCommentsByBlogId);
    }

    @GetMapping("/my-comments")
    public ResponseEntity<Page<CommentResponseDto>> getAllCommentsOfUser(
            @AuthenticationPrincipal User user,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<CommentResponseDto> allCommentsByUserId = commentService.getAllCommentsByUserId(user.getId(), page, size);
        return ResponseEntity.ok(allCommentsByUserId);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal User user) {
        commentService.deleteComment(commentId, user.getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update-comment/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @Valid @RequestBody UpdateCommentDto updateCommentDto,
            @PathVariable Long commentId,
            @AuthenticationPrincipal User user) {

        CommentResponseDto commentResponseDto = commentService.updateComment(updateCommentDto, commentId, user.getId());

        return ResponseEntity.ok(commentResponseDto);
    }


}
