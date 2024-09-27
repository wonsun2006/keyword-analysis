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
public class DocumentCount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "document_count_id")
	private Long id;
	@NotNull
	@Column(length = 255)
	String term;
	@Setter
	@NotNull
	@ColumnDefault("0")
	private int documentCount;
	@NotNull
	@JoinColumn(name = "document_collection_id")
	private Long documentCollectionId;

	protected DocumentCount() {
	}

	public DocumentCount(String term, int documentCount, Long documentCollectionId) {
		this.term = term;
		this.documentCount = documentCount;
		this.documentCollectionId = documentCollectionId;
	}
}
