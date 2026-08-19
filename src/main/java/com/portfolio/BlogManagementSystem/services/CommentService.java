package com.portfolio.BlogManagementSystem.services;

import com.portfolio.BlogManagementSystem.dtos.CommentResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateCommentDto;
import com.portfolio.BlogManagementSystem.dtos.UpdateCommentDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    CommentResponseDto createComment(CreateCommentDto createCommentDto, Long blogId, Long userId);

    Page<CommentResponseDto> getAllCommentsByBlogId(Long blogId, int page, int size);

    Page<CommentResponseDto> getAllCommentsByUserId(Long userId, int page, int size);

    CommentResponseDto updateComment(UpdateCommentDto updateCommentDto, Long commentId, Long userId);

    void deleteComment(Long commentId, Long userId);
}
