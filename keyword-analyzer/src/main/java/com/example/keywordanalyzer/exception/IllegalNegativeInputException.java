package com.example.keywordanalyzer.exception;

public class IllegalNegativeInputException extends RuntimeException {
	public IllegalNegativeInputException(String variableName) {
		super(variableName + " can not be negative value.");
	}
}
