package com.example.keywordanalyzer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.keywordanalyzer.model.entity.DocumentCount;

@Repository
public interface DocumentCountRepository extends JpaRepository<DocumentCount, Long> {
	Optional<DocumentCount> findByDocumentCollectionIdAndTermId(Long documentCollectionId, Long termId);
}
