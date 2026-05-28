package com.portfolio.BlogManagementSystem.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blogs")
@Getter
@Setter
@Builder

public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
