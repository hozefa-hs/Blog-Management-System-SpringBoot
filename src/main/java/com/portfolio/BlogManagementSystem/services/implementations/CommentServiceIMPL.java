package com.portfolio.BlogManagementSystem.services.implementations;

import com.portfolio.BlogManagementSystem.dtos.CommentResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateCommentDto;
import com.portfolio.BlogManagementSystem.entities.Blog;
import com.portfolio.BlogManagementSystem.entities.Comment;
import com.portfolio.BlogManagementSystem.entities.User;
import com.portfolio.BlogManagementSystem.repositories.BlogRepository;
import com.portfolio.BlogManagementSystem.repositories.CommentRepository;
import com.portfolio.BlogManagementSystem.repositories.UserRepository;
import com.portfolio.BlogManagementSystem.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceIMPL implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;


    @Override
    public CommentResponseDto createComment(@Valid CreateCommentDto createCommentDto, Long blogId, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User with Id " + userId + " not found while commenting on blog"));
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new IllegalArgumentException("Blog with Id " + blogId + " not found while commenting on blog"));

        Comment newComment = modelMapper.map(createCommentDto, Comment.class);

        newComment.setUser(user);
        newComment.setBlog(blog);

        Comment savedComment = commentRepository.save(newComment);
        return modelMapper.map(savedComment, CommentResponseDto.class);
    }
}















