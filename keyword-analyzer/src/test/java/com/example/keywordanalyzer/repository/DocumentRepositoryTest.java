package com.example.keywordanalyzer.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.keywordanalyzer.model.entity.Document;

@SpringBootTest
public class DocumentRepositoryTest extends BasicRepositoryTest<Document> {
	@Autowired
	public DocumentRepositoryTest(DocumentRepository repository) {
		super(repository);
	}

	@Override
	protected Long getId(Document entity) {
		return entity.getDocumentId();
	}

	@Override
	protected Document createMockData() {
		String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
		LocalDateTime createdAt = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
		Long collectionId = 1L;
		Long apiId = 1L;
		return new Document(content, createdAt, collectionId, apiId);
	}

	@Override
	protected void assertDataEquals(Document expected, Document actual) {
		assertEquals(expected.getContent(), actual.getContent());
		assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
		assertEquals(expected.getDocumentCollectionId(), actual.getDocumentCollectionId());
		assertEquals(expected.getApiId(), actual.getApiId());
	}
}
