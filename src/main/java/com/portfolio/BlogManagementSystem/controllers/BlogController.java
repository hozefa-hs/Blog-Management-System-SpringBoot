package com.portfolio.BlogManagementSystem.controllers;

import com.portfolio.BlogManagementSystem.dtos.BlogResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateBlogDto;
import com.portfolio.BlogManagementSystem.dtos.UpdateBlogDto;
import com.portfolio.BlogManagementSystem.entities.User;
import com.portfolio.BlogManagementSystem.services.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(
        name = "Blog APIs",
        description = "Operations related to blog management"
)
@RestController
@RequestMapping("/blogs")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication") //This annotation tells swagger that which controllers require JWT.
public class BlogController {

    private final BlogService blogService;


    @Operation(
            summary = "Create a new blog",
            description = "Allows authenticated users to create blogs"
    )
    @PostMapping("/create-blog")
    @PreAuthorize("hasAuthority('BLOG_WRITE')")
    public ResponseEntity<BlogResponseDto> createBlog(@RequestBody @Valid CreateBlogDto createBlogDto, @AuthenticationPrincipal User user) {
        BlogResponseDto response = blogService.createBlog(createBlogDto, user.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getAllMyBlogs")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Page<BlogResponseDto>> getAllBlogsByUserId(
            @AuthenticationPrincipal User user,
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size
    ) {
        Page<BlogResponseDto> blogsByUserId = blogService.getAllBlogsByUserId(user.getId(), page, size);
        return ResponseEntity.ok(blogsByUserId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all-blogs")
    public ResponseEntity<Page<BlogResponseDto>> getAllBlogs(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size
    ) {

        Page<BlogResponseDto> allBlogs = blogService.getAllBlogs(page, size);
        return ResponseEntity.ok(allBlogs);
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity<Void> deleteBlog(

            @Parameter(
                    description = "Unique ID of the blog",
                    example = "1",
                    required = true
            )
            @PathVariable Long blogId,
            @AuthenticationPrincipal User user) {
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
