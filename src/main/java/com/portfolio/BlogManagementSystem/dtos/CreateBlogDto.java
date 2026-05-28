package com.portfolio.BlogManagementSystem.dtos;

import com.portfolio.BlogManagementSystem.entities.User;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBlogDto {

    private String title;
    private String description;
}
