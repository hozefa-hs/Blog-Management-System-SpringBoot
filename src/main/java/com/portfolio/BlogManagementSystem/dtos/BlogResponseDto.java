package com.portfolio.BlogManagementSystem.dtos;

import com.portfolio.BlogManagementSystem.entities.User;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponseDto {

    private Long id;
    private String title;
    private String description;
    private Long userId;

}
