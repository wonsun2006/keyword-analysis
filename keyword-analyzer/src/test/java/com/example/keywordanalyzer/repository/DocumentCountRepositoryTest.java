package com.example.keywordanalyzer.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.keywordanalyzer.model.entity.DocumentCount;

@SpringBootTest
@Transactional
public class DocumentCountRepositoryTest extends BasicRepositoryTest<DocumentCount> {
	@Autowired
	public DocumentCountRepositoryTest(DocumentCountRepository repository) {
		super(repository);
	}

	@Override
	protected Long getId(DocumentCount entity) {
		return entity.getDocumentCountId();
	}

	@Override
	protected DocumentCount createMockData() {
		return new DocumentCount(1L, 1, 1L);
	}

	@Override
	protected void assertDataEquals(DocumentCount expected, DocumentCount actual) {
		assertEquals(expected.getTermId(), actual.getTermId());
		assertEquals(expected.getDocumentCount(), actual.getDocumentCount());
		assertEquals(expected.getDocumentCollectionId(), actual.getDocumentCollectionId());
	}

	@Test
	void updateDocumentCount() {
		// Arrange
		DocumentCount tempCount = createMockData();
		DocumentCount savedDocumentCount = repository.save(tempCount);
		int newCount = 2;
		DocumentCount newDocumentCount = new DocumentCount(1L, newCount, 1L);

		// Act
		savedDocumentCount.setDocumentCount(newCount);
		DocumentCount updatedDocumentCount = repository.save(savedDocumentCount);

		// Assert
		assertNotNull(updatedDocumentCount);
		assertEquals(getId(savedDocumentCount), getId(updatedDocumentCount));
		assertDataEquals(newDocumentCount, updatedDocumentCount);
	}
}
