package com.portfolio.BlogManagementSystem;

import com.portfolio.BlogManagementSystem.dtos.BlogResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateBlogDto;
import com.portfolio.BlogManagementSystem.services.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BlogTests {

    @Autowired
    BlogService blogService;

    @Test
    void createBlogTest() {

        CreateBlogDto createBlogDto = new CreateBlogDto("haaaaaa", "Dollars");
        BlogResponseDto blog = blogService.createBlog(createBlogDto, 1L);
        System.out.println(blog);

    }

    @Test
    void getAllBlogsTest() {
        List<BlogResponseDto> allBlogs = blogService.getAllBlogs();

        for (BlogResponseDto blog : allBlogs) {
            System.out.println(blog);
        }
    }
}
