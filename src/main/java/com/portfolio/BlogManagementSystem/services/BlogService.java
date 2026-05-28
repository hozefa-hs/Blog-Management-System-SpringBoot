package com.portfolio.BlogManagementSystem.services;

import com.portfolio.BlogManagementSystem.dtos.BlogResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateBlogDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {

    BlogResponseDto createBlog(CreateBlogDto createBlogDto, Long userId);

    List<BlogResponseDto> getAllBlogs();
}
