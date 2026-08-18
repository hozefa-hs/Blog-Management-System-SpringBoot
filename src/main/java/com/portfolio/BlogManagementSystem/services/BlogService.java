package com.portfolio.BlogManagementSystem.services;

import com.portfolio.BlogManagementSystem.dtos.BlogResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CommentResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateBlogDto;
import com.portfolio.BlogManagementSystem.dtos.UpdateBlogDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {

    BlogResponseDto createBlog(CreateBlogDto createBlogDto, Long userId);

    List<BlogResponseDto> getAllBlogs();

    List<BlogResponseDto> getAllBlogsByUserId(Long userId);

    void deleteBlog(Long userId, Long blogId);

    BlogResponseDto updateBlog(Long userId, Long blogId, UpdateBlogDto updateBlogDto);

}
