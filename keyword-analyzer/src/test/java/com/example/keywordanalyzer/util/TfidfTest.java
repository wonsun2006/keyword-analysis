package com.example.keywordanalyzer.util;

import static com.example.keywordanalyzer.util.NlpUtil.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.keywordanalyzer.exception.IllegalNegativeInputException;

public class TfidfTest {
	@Test
	void calculateTermFrequencySuccess() {
		// Assert
		int termCountInDocument = 5;
		double expected = 1 + Math.log10(5);

		// Act
		double actual = calculateTermFrequency(termCountInDocument);

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	void calculateTermFrequencyNegativeTermCountDocument() {
		// Assert
		int termCountInDocument = -3;

		// Act & Assert
		assertThrows(IllegalNegativeInputException.class, () -> calculateTermFrequency(termCountInDocument));
	}

	@Test
	void calculateInverseDocumentFrequencySuccess() {
		// Assert
		int totalDocumentCount = 25;
		int documentCountContainingTerm = 3;
		double expected = Math.log10(25.0 / 3);

		// Act
		double actual = calculateInverseDocumentFrequency(totalDocumentCount, documentCountContainingTerm);

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	void calculateInverseDocumentFrequencyNegativeTotalDocumentCount() {
		// Assert
		int totalDocumentCount = -5;
		int documentCountContainingTerm = 3;

		// Act & Assert
		assertThrows(IllegalNegativeInputException.class,
			() -> calculateInverseDocumentFrequency(totalDocumentCount, documentCountContainingTerm));
	}

	@Test
	void calculateInverseDocumentFrequencyNegativeDocumentCountContainingTerm() {
		// Assert
		int totalDocumentCount = 7;
		int documentCountContainingTerm = -2;

		// Act & Assert
		assertThrows(IllegalNegativeInputException.class,
			() -> calculateInverseDocumentFrequency(totalDocumentCount, documentCountContainingTerm));
	}

	@Test
	void calculateInverseDocumentFrequencyDocumentCountBiggerThanTotalDocumentCount() {
		// Assert
		int totalDocumentCount = 3;
		int documentCountContainingTerm = 4;

		// Act & Assert
		assertThrows(IllegalArgumentException.class,
			() -> calculateInverseDocumentFrequency(totalDocumentCount, documentCountContainingTerm));
	}

	@Test
	void calculateTfidfSuccess() {
		// Assert
		double tfValue = 1.2;
		double idfValue = 3.4;
		double expected = 4.08;

		// Act
		double actual = calculateTfidf(tfValue, idfValue);

		// Assert
		assertEquals(actual, expected);
	}

	@Test
	void calculateTfidfNegativeTfValue() {
		// Assert
		double tfValue = -3.6;
		double idfValue = 2;

		// Act & Assert
		assertThrows(IllegalNegativeInputException.class,
			() -> calculateTfidf(tfValue, idfValue));
	}

	@Test
	void calculateTfidfNegativeIdfValue() {
		// Assert
		double tfValue = 1.2;
		double idfValue = -3.4;

		// Act & Assert
		assertThrows(IllegalNegativeInputException.class,
			() -> calculateTfidf(tfValue, idfValue));
	}
}
