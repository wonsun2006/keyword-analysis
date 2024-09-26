package com.example.keywordanalyzer.exception;

public class EntityNotFoundException extends RuntimeException {
	public EntityNotFoundException(String entityName, Long id) {
		super(entityName + " not found. " + entityName + " Id : " + id);
	}
}
