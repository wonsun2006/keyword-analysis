package com.example.keywordanalyzer.model.entity;

import java.time.LocalDateTime;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class WordCollection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "collection_id")
	private Long id;
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
	private int totalDocCount;
	@Setter
	@NotNull
	private String message;

	protected WordCollection() {
	}

	public WordCollection(int collectStatus, int analysisStatus, LocalDateTime startTime,
		@Nullable LocalDateTime endTime,
		int totalDocCount, String message) {
		this.collectStatus = collectStatus;
		this.analysisStatus = analysisStatus;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalDocCount = totalDocCount;
		this.message = message;
	}
}
