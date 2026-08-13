package com.portfolio.BlogManagementSystem.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentDto {

    @NotBlank(message = "Comment cannot be empty")
    private String commentText;

}
