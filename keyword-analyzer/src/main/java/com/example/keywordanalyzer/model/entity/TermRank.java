package com.example.keywordanalyzer.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class TermRank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "term_rank_id")
	private Long id;
	@NotNull
	@Column(length = 255)
	private String term;
	@NotNull
	private int rankValue;
	@NotNull
	@JoinColumn(name = "document_collection_id")
	private Long documentCollectionId;

	protected TermRank() {

	}

	public TermRank(String term, int rankValue, Long documentCollectionId) {
		this.term = term;
		this.rankValue = rankValue;
		this.documentCollectionId = documentCollectionId;
	}
}
