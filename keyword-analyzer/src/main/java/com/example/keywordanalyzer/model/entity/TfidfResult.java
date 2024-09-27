package com.example.keywordanalyzer.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class TfidfResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tfidfResultId;
	@NotNull
	Long termId;
	@NotNull
	private double tfidfValue;
	@NotNull
	private double tfValue;
	@NotNull
	private double idfValue;
	@NotNull
	private Long documentId;
	@NotNull
	private Long documentCollectionId;

	protected TfidfResult() {
	}

	public TfidfResult(Long termId, double tfidfValue, double tfValue, double idfValue, Long documentId,
		Long documentCollectionId) {
		this.termId = termId;
		this.tfidfValue = tfidfValue;
		this.tfValue = tfValue;
		this.idfValue = idfValue;
		this.documentId = documentId;
		this.documentCollectionId = documentCollectionId;
	}
}
