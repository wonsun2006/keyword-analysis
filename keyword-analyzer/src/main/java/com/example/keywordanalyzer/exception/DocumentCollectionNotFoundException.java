package com.example.keywordanalyzer.exception;

public class DocumentCollectionNotFoundException extends EntityNotFoundException {
	public DocumentCollectionNotFoundException(Long documentCollectionId) {
		super("DocumentCollection", documentCollectionId);
	}
}
