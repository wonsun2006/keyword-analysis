package com.example.keywordanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.keywordanalyzer.model.entity.DocumentCollection;

@Repository
public interface DocumentCollectionRepository extends JpaRepository<DocumentCollection, Long> {
}
