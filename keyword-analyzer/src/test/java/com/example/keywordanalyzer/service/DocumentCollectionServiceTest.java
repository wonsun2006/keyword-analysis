package com.example.keywordanalyzer.service;

import static com.example.keywordanalyzer.util.TestDataGenerator.*;
import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.keywordanalyzer.model.entity.DocumentCollection;
import com.example.keywordanalyzer.model.entity.DocumentCount;
import com.example.keywordanalyzer.util.TestUtil;

@ExtendWith(MockitoExtension.class)
public class DocumentCollectionServiceTest {
	@InjectMocks
	private DocumentCollectionService documentCollectionService;

	@Test
	void calculateIdfResultMapSuccess() {
		// Arrange
		DocumentCollection documentCollection = generateDocumentCollection();
		List<DocumentCount> documentCountList = generateDocumentCounts();
		Map<Long, Double> expected = new HashMap<>();
		expected.put(1L, 0.0);
		expected.put(2L, 0.0);
		expected.put(3L, 0.0);
		expected.put(4L, log10(2));

		// Act
		Map<Long, Double> actual = documentCollectionService.calculateIdfResultMap(
			documentCollection.getTotalDocumentCount(), documentCountList);

		// Assert
		assertTrue(TestUtil.mapEntriesEquals(expected, actual));
	}
}
