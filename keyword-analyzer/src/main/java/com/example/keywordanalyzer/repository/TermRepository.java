package com.example.keywordanalyzer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.keywordanalyzer.model.entity.Term;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {
	Optional<Term> findByValue(String term);
}
