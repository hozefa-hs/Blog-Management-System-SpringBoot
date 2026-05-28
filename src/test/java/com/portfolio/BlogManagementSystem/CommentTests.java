package com.portfolio.BlogManagementSystem;

import com.portfolio.BlogManagementSystem.dtos.CommentResponseDto;
import com.portfolio.BlogManagementSystem.dtos.CreateCommentDto;
import com.portfolio.BlogManagementSystem.services.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentTests {

    @Autowired
    CommentService commentService;

    @Test
    void createCommentTest(){

        CreateCommentDto createCommentDto = new CreateCommentDto("First comment");

        CommentResponseDto comment = commentService.createComment(createCommentDto, 2L, 1L);

        System.out.println(comment);
    }
}
