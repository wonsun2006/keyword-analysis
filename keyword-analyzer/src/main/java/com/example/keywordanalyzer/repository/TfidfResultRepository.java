package com.example.keywordanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.keywordanalyzer.model.entity.TfidfResult;

@Repository
public interface TfidfResultRepository extends JpaRepository<TfidfResult, Long> {
}
