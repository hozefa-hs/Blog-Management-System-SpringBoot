package com.portfolio.BlogManagementSystem.services.implementations;

import com.portfolio.BlogManagementSystem.dtos.BlogResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CommentResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateBlogDto;
import com.portfolio.BlogManagementSystem.dtos.UpdateBlogDto;
import com.portfolio.BlogManagementSystem.entities.Blog;
import com.portfolio.BlogManagementSystem.entities.Comment;
import com.portfolio.BlogManagementSystem.entities.User;
import com.portfolio.BlogManagementSystem.enums.Role;
import com.portfolio.BlogManagementSystem.exceptions.ResourceNotFoundException;
import com.portfolio.BlogManagementSystem.repositories.BlogRepository;
import com.portfolio.BlogManagementSystem.repositories.UserRepository;
import com.portfolio.BlogManagementSystem.services.BlogService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<BlogResponseDto> getAllBlogsByUserId(Long userId) {
        List<Blog> allBlogsByUserIdList = blogRepository.findAllByUserId(userId);
        return allBlogsByUserIdList
                .stream()
                .map(blog -> modelMapper.map(blog, BlogResponseDto.class))
                .toList();
    }

    //@PreAuthorize("hasAuthority('BLOG_DELETE')")
    @Override
    public void deleteBlog(Long userId, Long blogId) {
        if (blogId == null) return;

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        if (user.getRole().equals(Role.USER)) {
            //Ownership check and does Blog exists done in single method
            //Ownership check means if the blog belongs to current user or not.
            blogRepository.findByIdAndUserId(blogId, userId).orElseThrow(() -> new ResourceNotFoundException("Blog with id " + blogId + " not found"));
        }
        //else condition when User is Admin
        else {
            blogRepository.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog with id " + blogId + " not found"));
        }


        //Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog with id " + blogId + " not found"));

        //Ownership check
        /*
        if (!blog.getUser().getId()
                .equals(userId)) {
            throw new AccessDeniedException("You can only delete your own blog");
        }*/

        blogRepository.deleteById(blogId);
    }

    @Override
    public BlogResponseDto updateBlog(Long userId, Long blogId, UpdateBlogDto updateBlogDto) {

        //Ownership check and does Blog exists done in single method
        Blog blog = blogRepository.findByIdAndUserId(blogId, userId).orElseThrow(() -> new ResourceNotFoundException("Blog not found with id : " + blogId));

        //Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog not found with id : " + blogId));

        //ownership check
        /*
        if (!blog.getUser().getId()
                .equals(userId)) {
            throw new AccessDeniedException("You can only update your own blog");
        }
        */

        blog.setTitle(updateBlogDto.getTitle());
        blog.setDescription(updateBlogDto.getDescription());

        Blog updatedBlog = blogRepository.save(blog);

        return modelMapper.map(updatedBlog, BlogResponseDto.class);
    }

}

