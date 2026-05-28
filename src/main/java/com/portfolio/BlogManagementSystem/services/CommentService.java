package com.portfolio.BlogManagementSystem.services;

import com.portfolio.BlogManagementSystem.dtos.CommentResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateCommentDto;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    CommentResponseDto createComment(CreateCommentDto createCommentDto, Long blogId, Long userId);
}
