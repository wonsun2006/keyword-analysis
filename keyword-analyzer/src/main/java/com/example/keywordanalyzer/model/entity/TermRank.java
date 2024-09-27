package com.example.keywordanalyzer.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class TermRank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long termRankId;
	@NotNull
	Long termId;
	@NotNull
	private int rankValue;
	@NotNull
	private Long documentCollectionId;

	protected TermRank() {

	}

	public TermRank(Long termId, int rankValue, Long documentCollectionId) {
		this.termId = termId;
		this.rankValue = rankValue;
		this.documentCollectionId = documentCollectionId;
	}
}
