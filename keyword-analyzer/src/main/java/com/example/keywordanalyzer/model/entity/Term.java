package com.example.keywordanalyzer.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Term {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long termId;
	@NotNull
	String value;

	protected Term() {
	}

	public Term(Long termId, String value) {
		this.termId = termId;
		this.value = value;
	}

	public Term(String value) {
		this.value = value;
	}

}
