package com.portfolio.BlogManagementSystem.controllers;

import com.portfolio.BlogManagementSystem.dtos.BlogResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateBlogDto;
import com.portfolio.BlogManagementSystem.dtos.UpdateBlogDto;
import com.portfolio.BlogManagementSystem.entities.User;
import com.portfolio.BlogManagementSystem.services.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/create-blog")
    @PreAuthorize("hasAuthority('BLOG_WRITE')")
    public ResponseEntity<BlogResponseDto> createBlog(@RequestBody @Valid CreateBlogDto createBlogDto, @AuthenticationPrincipal User user) {
        BlogResponseDto response = blogService.createBlog(createBlogDto, user.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getAllMyBlogs")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BlogResponseDto>> getAllBlogsByUserId(@AuthenticationPrincipal User user) {
        List<BlogResponseDto> blogsByUserId = blogService.getAllBlogsByUserId(user.getId());
        return ResponseEntity.ok(blogsByUserId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all-blogs")
    public ResponseEntity<List<BlogResponseDto>> getAllBlogs() {
        List<BlogResponseDto> allBlogs = blogService.getAllBlogs();
        return ResponseEntity.ok(allBlogs);
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long blogId, @AuthenticationPrincipal User user){
        blogService.deleteBlog(user.getId(), blogId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity<BlogResponseDto> updateBlog(
            @PathVariable Long blogId,
            @Valid @RequestBody UpdateBlogDto updateBlogDto,
            @AuthenticationPrincipal User user) {

        BlogResponseDto response = blogService.updateBlog(user.getId(), blogId, updateBlogDto);

        return ResponseEntity.ok(response);
    }

}
