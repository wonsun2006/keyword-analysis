package com.example.keywordanalyzer.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.keywordanalyzer.model.constants.AnalysisStatus;
import com.example.keywordanalyzer.model.constants.CollectStatus;
import com.example.keywordanalyzer.model.entity.Document;
import com.example.keywordanalyzer.model.entity.DocumentCollection;
import com.example.keywordanalyzer.model.entity.DocumentCount;
import com.example.keywordanalyzer.model.entity.Term;
import com.example.keywordanalyzer.model.entity.TermCount;
import com.example.keywordanalyzer.repository.DocumentCollectionRepository;
import com.example.keywordanalyzer.repository.DocumentCountRepository;
import com.example.keywordanalyzer.repository.TermCountRepository;
import com.example.keywordanalyzer.repository.TermRepository;

@SpringBootTest
@Transactional
public class TermCountServiceIntegrationTest {
	private final TermCountRepository termCountRepository;
	private final DocumentCountRepository documentCountRepository;
	private final TermCountService service;
	private final TermRepository termRepository;
	private final DocumentCollectionRepository documentCollectionRepository;

	@Autowired
	public TermCountServiceIntegrationTest(TermCountRepository termCountRepository,
		DocumentCountRepository documentCountRepository,
		TermCountService service, TermRepository termRepository,
		DocumentCollectionRepository documentCollectionRepository) {
		this.termCountRepository = termCountRepository;
		this.documentCountRepository = documentCountRepository;
		this.service = service;
		this.termRepository = termRepository;
		this.documentCollectionRepository = documentCollectionRepository;
	}

	@Test
	void saveTermCountWithSinglePost() {
		// Arrange
		DocumentCollection documentCollection = new DocumentCollection(CollectStatus.COMPLETED,
			AnalysisStatus.COMPLETED,
			LocalDateTime.of(2024, 9, 27, 6, 40), null, 1, "");
		documentCollection = documentCollectionRepository.save(documentCollection);
		Long documentCollectionId = documentCollection.getDocumentCollectionId();
		Document document = new Document(1L, "Hello World! This test is post test.",
			LocalDateTime.of(2024, 1, 1, 0, 0, 0),
			documentCollectionId, 1L);
		Long documentId = document.getDocumentId();

		// Act
		service.saveTermCount(document);

		// Assert
		HashMap<String, Integer> termCountTuples = new HashMap<>();
		termCountTuples.put("World", 1);
		termCountTuples.put("test", 2);
		termCountTuples.put("post", 1);

		for (String term : termCountTuples.keySet()) {
			Optional<Term> termEntity = termRepository.findByValue(term);
			assertTrue(termEntity.isPresent());
			Long termId = termEntity.get().getTermId();

			// TermCount 생성 확인
			Optional<TermCount> termCountOptional = termCountRepository.findByDocumentIdAndTermId(documentId, termId);
			assertTrue(termCountOptional.isPresent());
			TermCount termCount = termCountOptional.orElseThrow();
			int expectedTermCount = termCountTuples.get(term);
			int actualTermCount = termCount.getTermCount();
			assertEquals(expectedTermCount, actualTermCount);

			// DocumentCount 업데이트 확인
			Optional<DocumentCount> documentCountOptional = documentCountRepository.findByDocumentCollectionIdAndTermId(
				documentCollectionId, termId);
			assertTrue(documentCountOptional.isPresent());
			DocumentCount documentCount = documentCountOptional.orElseThrow();
			int actualDocumentCount = documentCount.getDocumentCount();
			assertEquals(expectedTermCount, actualDocumentCount);
		}

	}
}
