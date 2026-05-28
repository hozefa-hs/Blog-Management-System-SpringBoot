package com.portfolio.BlogManagementSystem.services;

import com.portfolio.BlogManagementSystem.dtos.BlogResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateBlogDto;
import org.springframework.stereotype.Service;

@Service
public interface BlogService {

    BlogResponseDto createBlog(CreateBlogDto createBlogDto, Long userId);
}
