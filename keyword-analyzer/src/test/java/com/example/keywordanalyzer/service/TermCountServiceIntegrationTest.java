package com.example.keywordanalyzer.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.keywordanalyzer.model.entity.DocumentCount;
import com.example.keywordanalyzer.model.entity.Post;
import com.example.keywordanalyzer.model.entity.TermCount;
import com.example.keywordanalyzer.repository.DocumentCountRepository;
import com.example.keywordanalyzer.repository.TermCountRepository;

@SpringBootTest
@Transactional
public class TermCountServiceIntegrationTest {
	private final TermCountRepository termCountRepository;
	private final DocumentCountRepository documentCountRepository;
	private final TermCountService service;

	@Autowired
	public TermCountServiceIntegrationTest(TermCountRepository termCountRepository,
		DocumentCountRepository documentCountRepository,
		TermCountService service) {
		this.termCountRepository = termCountRepository;
		this.documentCountRepository = documentCountRepository;
		this.service = service;
	}

	@Test
	void saveTermCountWithSinglePost() {
		// Arrange
		long collectionId = 1L;
		long postId = 1L;
		Post post = new Post(postId, "Hello World! This test is post test.", LocalDateTime.of(2024, 1, 1, 0, 0, 0),
			collectionId, 1L);

		// Act
		service.saveTermCount(post);

		// Assert
		HashMap<String, Integer> termCountTuples = new HashMap<>();
		termCountTuples.put("World", 1);
		termCountTuples.put("test", 2);
		termCountTuples.put("post", 1);

		for (String term : termCountTuples.keySet()) {
			// TermCount 생성 확인
			Optional<TermCount> termCountOptional = termCountRepository.findByPostIdAndTerm(postId, term);
			assertTrue(termCountOptional.isPresent());
			TermCount termCount = termCountOptional.orElseThrow();
			int expectedTermCount = termCountTuples.get(term);
			int actualTermCount = termCount.getTermCount();
			assertEquals(expectedTermCount, actualTermCount);

			// DocumentCount 업데이트 확인
			Optional<DocumentCount> documentCountOptional = documentCountRepository.findByCollectionIdAndTerm(
				collectionId, term);
			assertTrue(documentCountOptional.isPresent());
			DocumentCount documentCount = documentCountOptional.orElseThrow();
			int actualDocumentCount = documentCount.getDocumentCount();
			assertEquals(expectedTermCount, actualDocumentCount);
		}

	}
}
