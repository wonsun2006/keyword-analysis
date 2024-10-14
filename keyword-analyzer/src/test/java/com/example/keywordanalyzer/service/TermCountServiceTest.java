package com.example.keywordanalyzer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.keywordanalyzer.model.entity.Document;
import com.example.keywordanalyzer.model.entity.DocumentCollection;
import com.example.keywordanalyzer.model.entity.DocumentCount;
import com.example.keywordanalyzer.model.entity.Term;
import com.example.keywordanalyzer.model.entity.TermCount;
import com.example.keywordanalyzer.repository.DocumentCollectionRepository;
import com.example.keywordanalyzer.repository.DocumentCountRepository;
import com.example.keywordanalyzer.repository.TermCountRepository;
import com.example.keywordanalyzer.repository.TermRepository;
import com.example.keywordanalyzer.util.NlpUtil;
import com.example.keywordanalyzer.util.TestUtil;

@ExtendWith(MockitoExtension.class)
public class TermCountServiceTest {
	@Mock
	private DocumentCollection documentCollection;
	@Mock
	private TermCountRepository termCountRepository;
	@Mock
	private DocumentCollectionRepository documentCollectionRepository;
	@Mock
	private DocumentCountRepository documentCountRepository;
	@Mock
	private TermRepository termRepository;

	@InjectMocks
	private TermCountService service;

	@BeforeEach
	void setUp() {
		Mockito.when(documentCollection.getDocumentCollectionId()).thenReturn(1L);
		Mockito.when(documentCollectionRepository.existsById(documentCollection.getDocumentCollectionId()))
			.thenReturn(true);
	}

	@Test
	void saveTermCountWithSingleDocument() {
		// Arrange
		Document document = new Document(1L, "Hello World! This test is test post.",
			LocalDateTime.of(2024, 1, 1, 0, 0, 0),
			documentCollection.getDocumentCollectionId(), 1L);
		Mockito.doReturn(Optional.of(new Term(1L, "World"))).when(termRepository).findByValue("World");
		Mockito.doReturn(Optional.of(new Term(2L, "test"))).when(termRepository).findByValue("test");
		Mockito.doReturn(Optional.of(new Term(3L, "post"))).when(termRepository).findByValue("post");

		// Act
		service.saveTermCount(document);

		// Assert
		Map<String, Integer> actualCounts = NlpUtil.extractNoun(document.getContent());
		Map<String, Integer> expectedCounts = new HashMap<>();
		expectedCounts.put("World", 1);
		expectedCounts.put("test", 2);
		expectedCounts.put("post", 1);
		int totalTermCount = expectedCounts.size();

		assertTrue(TestUtil.mapEntriesEquals(expectedCounts, actualCounts));
		verify(termCountRepository, times(totalTermCount)).save(any(TermCount.class));
		verify(documentCountRepository, times(totalTermCount)).save(any(DocumentCount.class));
	}
}
