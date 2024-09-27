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
	@Column(length = 255)
	String term;
	@Setter
	@NotNull
	@ColumnDefault("0")
	private int termCount;
	@NotNull
	@JoinColumn(name = "document_id")
	private Long documentId;

	protected TermCount() {
	}

	public TermCount(String term, int termCount, Long documentId) {
		this.term = term;
		this.termCount = termCount;
		this.documentId = documentId;
	}
}
