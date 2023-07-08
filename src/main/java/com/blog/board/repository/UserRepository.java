package com.blog.board.repository;

import com.blog.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public boolean existsByUserEmailAndUserPassword(String userEmail, String userPassword);
}
