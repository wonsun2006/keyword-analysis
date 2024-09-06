package com.example.keywordanalyzer.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.keywordanalyzer.model.entity.DocumentCount;

@SpringBootTest
public class DocumentCountRepositoryTest extends BasicRepositoryTest<DocumentCount> {
	@Autowired
	public DocumentCountRepositoryTest(DocumentCountRepository repository) {
		super(repository);
	}

	@Override
	protected Long getId(DocumentCount entity) {
		return entity.getId();
	}

	protected DocumentCount createMockData() {
		String term = "Lorem";
		int count = 1;
		Long postId = 1L;
		return new DocumentCount(term, count, postId);
	}

	@Override
	protected void assertDataEquals(DocumentCount expected, DocumentCount actual) {
		assertEquals(expected.getTerm(), actual.getTerm());
		assertEquals(expected.getDocumentCount(), actual.getDocumentCount());
		assertEquals(expected.getCollectionId(), actual.getCollectionId());
	}

	@Test
	void updateDocumentCount() {
		// Arrange
		DocumentCount tempCount = createMockData();
		DocumentCount savedDocumentCount = repository.save(tempCount);
		int newCount = 2;
		DocumentCount newDocumentCount = new DocumentCount("Lorem", newCount, 1L);

		// Act
		savedDocumentCount.setDocumentCount(newCount);
		DocumentCount updatedDocumentCount = repository.save(savedDocumentCount);

		// Assert
		assertNotNull(updatedDocumentCount);
		assertEquals(getId(savedDocumentCount), getId(updatedDocumentCount));
		assertDataEquals(newDocumentCount, updatedDocumentCount);
	}
}
