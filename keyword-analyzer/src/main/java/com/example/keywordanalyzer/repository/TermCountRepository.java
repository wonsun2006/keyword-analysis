package com.example.keywordanalyzer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.keywordanalyzer.model.entity.TermCount;

@Repository
public interface TermCountRepository extends JpaRepository<TermCount, Long> {
	Optional<TermCount> findByPostIdAndTerm(long postId, String term);
}
