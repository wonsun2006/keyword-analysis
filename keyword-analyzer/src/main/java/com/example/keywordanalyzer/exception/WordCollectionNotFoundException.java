package com.example.keywordanalyzer.exception;

public class WordCollectionNotFoundException extends EntityNotFoundException {
	public WordCollectionNotFoundException(Long wordCollectionId) {
		super("WordCollection", wordCollectionId);
	}
}
