package com.portfolio.BlogManagementSystem.services.implementations;

import com.portfolio.BlogManagementSystem.dtos.BlogResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateBlogDto;
import com.portfolio.BlogManagementSystem.entities.Blog;
import com.portfolio.BlogManagementSystem.entities.User;
import com.portfolio.BlogManagementSystem.repositories.BlogRepository;
import com.portfolio.BlogManagementSystem.repositories.UserRepository;
import com.portfolio.BlogManagementSystem.services.BlogService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceIMPL implements BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BlogResponseDto createBlog(@Valid CreateBlogDto createBlogDto, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found while creating blog"));

        Blog newBlog = modelMapper.map(createBlogDto, Blog.class);
        newBlog.setUser(user);
        Blog savedBlog = blogRepository.save(newBlog);
        return modelMapper.map(savedBlog, BlogResponseDto.class);
    }

    @Override
    public List<BlogResponseDto> getAllBlogs() {
        List<Blog> blogList = blogRepository.findAll();
        return blogList
                .stream()
                .map(blog -> modelMapper.map(blog, BlogResponseDto.class))
                .toList();
    }
}














