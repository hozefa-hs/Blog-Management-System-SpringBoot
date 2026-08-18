package com.portfolio.BlogManagementSystem.services.implementations;

import com.portfolio.BlogManagementSystem.dtos.CommentResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateCommentDto;
import com.portfolio.BlogManagementSystem.dtos.UpdateCommentDto;
import com.portfolio.BlogManagementSystem.entities.Blog;
import com.portfolio.BlogManagementSystem.entities.Comment;
import com.portfolio.BlogManagementSystem.entities.User;
import com.portfolio.BlogManagementSystem.exceptions.ResourceNotFoundException;
import com.portfolio.BlogManagementSystem.repositories.BlogRepository;
import com.portfolio.BlogManagementSystem.repositories.CommentRepository;
import com.portfolio.BlogManagementSystem.repositories.UserRepository;
import com.portfolio.BlogManagementSystem.services.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceIMPL implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public CommentResponseDto createComment(CreateCommentDto createCommentDto, Long blogId, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with Id " + userId + " not found while commenting on blog"));
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog with Id " + blogId + " not found while commenting on blog"));

        Comment newComment = modelMapper.map(createCommentDto, Comment.class);

        newComment.setUser(user);
        newComment.setBlog(blog);

        Comment savedComment = commentRepository.save(newComment);
        return modelMapper.map(savedComment, CommentResponseDto.class);
    }


    @Override
    public List<CommentResponseDto> getAllCommentsByBlogId(Long blogId) {
        blogRepository.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog with id " + blogId + " not found"));
        List<Comment> commentList = commentRepository.findAllByBlogId(blogId);
        return commentList.stream().map(comment -> modelMapper.map(comment, CommentResponseDto.class)).toList();
    }

    @Override
    public List<CommentResponseDto> getAllCommentsByUserId(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        List<Comment> commentList = commentRepository.findAllByUserId(userId);
        return commentList.stream().map(comment -> modelMapper.map(comment, CommentResponseDto.class)).toList();
    }

    @Override
    public CommentResponseDto updateComment(UpdateCommentDto updateCommentDto, Long commentId, Long userId) {

        Comment comment = commentRepository.findByIdAndUserId(commentId, userId).orElseThrow(() -> new ResourceNotFoundException("Comment with id " + commentId + " not found"));

        comment.setCommentText(updateCommentDto.getCommentText());

        Comment saved = commentRepository.save(comment);

        return modelMapper.map(saved, CommentResponseDto.class);

    }

    @Override
    public void deleteComment(Long commentId, Long userId) {
        if (commentId == null) return;
        commentRepository.findByIdAndUserId(commentId, userId).orElseThrow(() -> new ResourceNotFoundException("Comment with id " + commentId + " not found"));

        commentRepository.deleteById(commentId);
    }
}

