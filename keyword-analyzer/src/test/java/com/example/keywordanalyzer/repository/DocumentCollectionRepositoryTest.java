package com.example.keywordanalyzer.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.keywordanalyzer.model.constants.AnalysisStatus;
import com.example.keywordanalyzer.model.constants.CollectStatus;
import com.example.keywordanalyzer.model.entity.DocumentCollection;

@SpringBootTest
public class DocumentCollectionRepositoryTest extends BasicRepositoryTest<DocumentCollection> {
	@Autowired
	public DocumentCollectionRepositoryTest(DocumentCollectionRepository repository) {
		super(repository);
	}

	@Override
	protected Long getId(DocumentCollection entity) {
		return entity.getId();
	}

	@Override
	protected DocumentCollection createMockData() {
		int collectStatus = CollectStatus.NOT_STARTED;
		int analysisStatus = AnalysisStatus.NOT_STARTED;
		LocalDateTime startTime = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
		LocalDateTime endTime = LocalDateTime.of(2024, 1, 1, 1, 0, 0);
		int totalDocumentCount = 0;
		String message = "test";
		return new DocumentCollection(collectStatus, analysisStatus, startTime, endTime, totalDocumentCount, message);
	}

	@Override
	protected void assertDataEquals(DocumentCollection expected, DocumentCollection actual) {
		assertEquals(expected.getCollectStatus(), actual.getCollectStatus());
		assertEquals(expected.getAnalysisStatus(), actual.getAnalysisStatus());
		assertEquals(expected.getStartTime(), actual.getStartTime());
		assertEquals(expected.getEndTime(), actual.getEndTime());
		assertEquals(expected.getTotalDocumentCount(), actual.getTotalDocumentCount());
		assertEquals(expected.getMessage(), actual.getMessage());
	}

	@Test
	void updateWordCollection() {
		// Arrange
		DocumentCollection collection = createMockData();
		DocumentCollection savedDocumentCollection = repository.save(collection);
		int newCollectStatus = CollectStatus.IN_PROGRESS;
		int newAnalysisStatus = AnalysisStatus.IN_PROGRESS;
		LocalDateTime newStartTime = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
		LocalDateTime newEndTime = LocalDateTime.of(2024, 1, 1, 2, 0, 0);
		int newTotalDocumentCount = 1;
		String newMessage = "test2";
		DocumentCollection newCollection = new DocumentCollection(newCollectStatus, newAnalysisStatus, newStartTime,
			newEndTime, newTotalDocumentCount, newMessage);

		// Act
		savedDocumentCollection.setCollectStatus(newCollectStatus);
		savedDocumentCollection.setAnalysisStatus(newAnalysisStatus);
		savedDocumentCollection.setEndTime(newEndTime);
		savedDocumentCollection.setTotalDocumentCount(newTotalDocumentCount);
		savedDocumentCollection.setMessage(newMessage);
		DocumentCollection updatedDocumentCollection = repository.save(savedDocumentCollection);

		// Assert
		assertNotNull(updatedDocumentCollection);
		assertEquals(savedDocumentCollection.getId(), updatedDocumentCollection.getId());
		assertDataEquals(newCollection, updatedDocumentCollection);
	}
}
