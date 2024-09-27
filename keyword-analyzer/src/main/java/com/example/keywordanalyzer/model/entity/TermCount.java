package com.example.keywordanalyzer.model.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class TermCount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "term_count_id")
	private Long id;
	@NotNull
	@JoinColumn(name = "term_id")
	Long termId;
	@Setter
	@NotNull
	@ColumnDefault("0")
	private int termCount;
	@NotNull
	@JoinColumn(name = "document_id")
	private Long documentId;
	@NotNull
	@JoinColumn(name = "document_collection_id")
	private Long documentCollectionId;

	protected TermCount() {
	}

	public TermCount(Long termId, int termCount, Long documentId, Long documentCollectionId) {
		this.termId = termId;
		this.termCount = termCount;
		this.documentId = documentId;
		this.documentCollectionId = documentCollectionId;
	}
}
