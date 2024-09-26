package com.example.keywordanalyzer.exception;

public class EntityNotFoundException extends RuntimeException {
	public EntityNotFoundException(String entityName, Long Id) {
		super(entityName + " not found. " + entityName + " Id : " + Id);
	}
}
