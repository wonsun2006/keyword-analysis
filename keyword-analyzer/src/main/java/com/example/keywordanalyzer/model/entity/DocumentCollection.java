package com.example.keywordanalyzer.model.entity;

import java.time.LocalDateTime;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class DocumentCollection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long documentCollectionId;
	@Setter
	@NotNull
	private int collectStatus;
	@Setter
	@NotNull
	private int analysisStatus;
	@NotNull
	private LocalDateTime startTime;
	@Setter
	@Nullable
	private LocalDateTime endTime;
	@Setter
	@NotNull
	private int totalDocumentCount;
	@Setter
	@NotNull
	private String message;

	protected DocumentCollection() {
	}

	public DocumentCollection(int collectStatus, int analysisStatus, LocalDateTime startTime,
		@Nullable LocalDateTime endTime,
		int totalDocumentCount, String message) {
		this.collectStatus = collectStatus;
		this.analysisStatus = analysisStatus;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalDocumentCount = totalDocumentCount;
		this.message = message;
	}

	public DocumentCollection(long documentCollectionId, int collectStatus, int analysisStatus, LocalDateTime startTime,
		@Nullable LocalDateTime endTime, int totalDocumentCount, String message) {
		this.documentCollectionId = documentCollectionId;
		this.collectStatus = collectStatus;
		this.analysisStatus = analysisStatus;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalDocumentCount = totalDocumentCount;
		this.message = message;
	}
}
