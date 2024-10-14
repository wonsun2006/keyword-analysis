package com.example.keywordanalyzer.service;

import static com.example.keywordanalyzer.util.TestDataGenerator.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.example.keywordanalyzer.exception.DocumentCollectionNotFoundException;
import com.example.keywordanalyzer.model.entity.Document;
import com.example.keywordanalyzer.model.entity.DocumentCollection;
import com.example.keywordanalyzer.model.entity.DocumentCount;
import com.example.keywordanalyzer.model.entity.TermCount;
import com.example.keywordanalyzer.model.entity.TfidfResult;
import com.example.keywordanalyzer.repository.DocumentCollectionRepository;
import com.example.keywordanalyzer.repository.DocumentCountRepository;
import com.example.keywordanalyzer.repository.DocumentRepository;
import com.example.keywordanalyzer.repository.TermCountRepository;

@SpringBootTest
@Transactional
public class DocumentCollectionServiceIntegrationTest {
	private final DocumentCollectionService documentCollectionService;
	private final DocumentRepository documentRepository;
	private final DocumentCountRepository documentCountRepository;
	private final DocumentCollectionRepository documentCollectionRepository;
	private final JdbcTemplate jdbcTemplate;
	@Autowired
	private TermCountRepository termCountRepository;

	@Autowired
	public DocumentCollectionServiceIntegrationTest(DocumentCollectionService documentCollectionService,
		DocumentRepository documentRepository, DocumentCountRepository documentCountRepository,
		DocumentCollectionRepository documentCollectionRepository,
		JdbcTemplate jdbcTemplate) {
		this.documentCollectionService = documentCollectionService;
		this.documentRepository = documentRepository;
		this.documentCountRepository = documentCountRepository;
		this.documentCollectionRepository = documentCollectionRepository;
		this.jdbcTemplate = jdbcTemplate;
	}

	boolean tfidfResultEquals(TfidfResult expected, TfidfResult actual) {
		return expected.getTfValue() == actual.getTfValue() &&
			expected.getIdfValue() == actual.getIdfValue() &&
			expected.getTfidfValue() == actual.getTfidfValue() &&
			Objects.equals(expected.getTermId(), actual.getTermId()) &&
			Objects.equals(expected.getDocumentId(), actual.getDocumentId()) &&
			Objects.equals(expected.getDocumentCollectionId(), actual.getDocumentCollectionId());
	}

	@BeforeEach
	void resetAutoIncrement() {
		jdbcTemplate.execute("ALTER TABLE document_collection AUTO_INCREMENT = 1");
		jdbcTemplate.execute("ALTER TABLE document AUTO_INCREMENT = 1");
		jdbcTemplate.execute("ALTER TABLE tfidf_result AUTO_INCREMENT = 1");
	}

	void saveExampleEntities() {
		DocumentCollection documentCollection = generateDocumentCollection();
		documentCollectionRepository.save(documentCollection);
		List<Document> documentList = generateDocuments();
		documentRepository.saveAll(documentList);
		List<TermCount> termCountList = generateTermCounts();
		termCountRepository.saveAll(termCountList);
		List<DocumentCount> documentCountList = generateDocumentCounts();
		documentCountRepository.saveAll(documentCountList);
	}

	@Test
	void analyzeDocumentCollectionSuccess() {
		// Arrange
		saveExampleEntities();

		// Act
		List<TfidfResult> actualList = documentCollectionService.analyzeDocumentCollection(1L);

		// Assert
		List<TfidfResult> expectedList = generateTfidfResults();
		for (TfidfResult expected : expectedList) {
			boolean exists = false;
			for (TfidfResult actual : actualList) {
				exists |= tfidfResultEquals(expected, actual);
			}
			assertTrue(exists);
		}
	}

	@Test
	void analyzeDocumentCollectionWithWrongDocumentCollectionId() {
		// Arrange
		saveExampleEntities();

		// Act & Assert
		assertThrows(DocumentCollectionNotFoundException.class,
			() -> documentCollectionService.analyzeDocumentCollection(100L));
	}
}
