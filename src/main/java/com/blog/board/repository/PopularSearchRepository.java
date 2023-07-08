package com.blog.board.repository;

import com.blog.board.entity.PopularSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopularSearchRepository extends JpaRepository<PopularSearch, String> {
}
