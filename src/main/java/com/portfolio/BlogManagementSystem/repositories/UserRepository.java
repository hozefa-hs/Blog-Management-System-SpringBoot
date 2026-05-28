package com.portfolio.BlogManagementSystem.repositories;

import com.portfolio.BlogManagementSystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
