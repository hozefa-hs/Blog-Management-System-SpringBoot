package com.portfolio.BlogManagementSystem.dtos;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String commentText;
    private Long blogId;
    private Long userId;

}
