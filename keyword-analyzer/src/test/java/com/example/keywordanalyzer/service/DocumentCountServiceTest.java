package com.example.keywordanalyzer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.keywordanalyzer.model.entity.DocumentCount;
import com.example.keywordanalyzer.repository.DocumentCountRepository;

@ExtendWith(MockitoExtension.class)
public class DocumentCountServiceTest {
	@Mock
	private DocumentCountRepository documentCountRepository;

	@InjectMocks
	private DocumentCountService documentCountService;

	@Test
	void updateDocumentCountSuccess() {
		// Arrange
		DocumentCount documentCount = new DocumentCount(1L, 5, 1L);

		// Act
		int count = documentCount.getDocumentCount();
		documentCount.setDocumentCount(count + 1);
		documentCountService.updateDocumentCount(documentCount);

		// Assert
		verify(documentCountRepository, times(1)).save(documentCount);
		assertEquals(6, documentCount.getDocumentCount());
	}

	@Test
	void updateDocumentCountWrongInputFormat() {
		// Arrange
		DocumentCount documentCount = new DocumentCount(1L, -1, 1L);

		// Act, Assert
		assertThrows(IllegalArgumentException.class,
			() -> documentCountService.updateDocumentCount(documentCount));
	}
}
