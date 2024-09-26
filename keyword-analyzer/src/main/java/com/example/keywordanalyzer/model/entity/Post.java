package com.example.keywordanalyzer.model.entity;

import java.time.LocalDateTime;

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
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String content;
	@NotNull
	private LocalDateTime createdAt;
	@NotNull
	@JoinColumn(name = "collection_id")
	private Long collectionId;
	@NotNull
	private Long apiId;

	protected Post() {
	}

	public Post(Long id, String content, LocalDateTime createdAt, Long collectionId, Long apiId) {
		this.id = id;
		this.content = content;
		this.createdAt = createdAt;
		this.collectionId = collectionId;
		this.apiId = apiId;
	}

	public Post(String content, LocalDateTime createdAt, Long collectionId, Long apiId) {
		this.content = content;
		this.createdAt = createdAt;
		this.collectionId = collectionId;
		this.apiId = apiId;
	}
}
