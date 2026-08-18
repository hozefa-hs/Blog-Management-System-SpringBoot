package com.portfolio.BlogManagementSystem.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentDto {

    @NotBlank(message = "comment cannot be empty")
    private String commentText;
}
