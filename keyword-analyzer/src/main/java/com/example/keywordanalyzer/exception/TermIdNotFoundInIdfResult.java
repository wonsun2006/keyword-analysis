package com.example.keywordanalyzer.exception;

public class TermIdNotFoundInIdfResult extends RuntimeException {
	public TermIdNotFoundInIdfResult(Long termId) {
		super("No such term ID with " + termId + " exists in IDF result map.");
	}
}
