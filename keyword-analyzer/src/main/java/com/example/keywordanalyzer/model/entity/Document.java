package com.example.keywordanalyzer.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long documentId;
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String content;
	@NotNull
	private LocalDateTime createdAt;
	@NotNull
	private Long documentCollectionId;
	@NotNull
	private Long apiId;

	protected Document() {
	}

	public Document(Long documentId, String content, LocalDateTime createdAt, Long documentCollectionId, Long apiId) {
		this.documentId = documentId;
		this.content = content;
		this.createdAt = createdAt;
		this.documentCollectionId = documentCollectionId;
		this.apiId = apiId;
	}

	public Document(String content, LocalDateTime createdAt, Long documentCollectionId, Long apiId) {
		this.content = content;
		this.createdAt = createdAt;
		this.documentCollectionId = documentCollectionId;
		this.apiId = apiId;
	}
}
