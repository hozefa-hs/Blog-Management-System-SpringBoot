package com.portfolio.BlogManagementSystem.services;

import com.portfolio.BlogManagementSystem.dtos.BlogResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CommentResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateBlogDto;
import com.portfolio.BlogManagementSystem.dtos.UpdateBlogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {

    BlogResponseDto createBlog(CreateBlogDto createBlogDto, Long userId);

    Page<BlogResponseDto> getAllBlogs(int page, int size);

    Page<BlogResponseDto> getAllBlogsByUserId(Long userId, int page, int size);

    void deleteBlog(Long userId, Long blogId);

    BlogResponseDto updateBlog(Long userId, Long blogId, UpdateBlogDto updateBlogDto);

}
