package com.portfolio.BlogManagementSystem.services;

import com.portfolio.BlogManagementSystem.dtos.CommentResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateCommentDto;
import com.portfolio.BlogManagementSystem.dtos.UpdateCommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    CommentResponseDto createComment(CreateCommentDto createCommentDto, Long blogId, Long userId);

    List<CommentResponseDto> getAllCommentsByBlogId(Long blogId);

    List<CommentResponseDto> getAllCommentsByUserId(Long userId);

    CommentResponseDto updateComment(UpdateCommentDto updateCommentDto, Long commentId, Long userId);

    void deleteComment(Long commentId, Long userId);
}
