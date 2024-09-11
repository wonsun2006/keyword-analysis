package com.example.keywordanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.keywordanalyzer.model.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
