package com.blog.board.repository;

import com.blog.board.entity.Board;
import com.blog.board.entity.Liky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikyRepository extends JpaRepository<Liky, Integer> {
}
