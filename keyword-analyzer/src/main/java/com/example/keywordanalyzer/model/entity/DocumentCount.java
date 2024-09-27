package com.example.keywordanalyzer.model.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class DocumentCount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long documentCountId;
	@NotNull
	Long termId;
	@Setter
	@NotNull
	@ColumnDefault("0")
	private int documentCount;
	@NotNull
	private Long documentCollectionId;

	protected DocumentCount() {
	}

	public DocumentCount(Long termId, int documentCount, Long documentCollectionId) {
		this.termId = termId;
		this.documentCount = documentCount;
		this.documentCollectionId = documentCollectionId;
	}
}
