package com.example.keywordanalyzer.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.keywordanalyzer.model.constants.AnalysisStatus;
import com.example.keywordanalyzer.model.constants.CollectStatus;
import com.example.keywordanalyzer.model.entity.WordCollection;

@SpringBootTest
public class WordCollectionRepositoryTest extends BasicRepositoryTest<WordCollection> {
	@Autowired
	public WordCollectionRepositoryTest(WordCollectionRepository repository) {
		super(repository);
	}

	@Override
	protected Long getId(WordCollection entity) {
		return entity.getId();
	}

	protected WordCollection createMockData() {
		int collectStatus = CollectStatus.NOT_STARTED;
		int analysisStatus = AnalysisStatus.NOT_STARTED;
		LocalDateTime startTime = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
		LocalDateTime endTime = LocalDateTime.of(2024, 1, 1, 1, 0, 0);
		int totalDocCount = 0;
		String message = "test";
		return new WordCollection(collectStatus, analysisStatus, startTime, endTime, totalDocCount, message);
	}

	@Override
	protected void assertDataEquals(WordCollection expected, WordCollection actual) {
		assertEquals(expected.getCollectStatus(), actual.getCollectStatus());
		assertEquals(expected.getAnalysisStatus(), actual.getAnalysisStatus());
		assertEquals(expected.getStartTime(), actual.getStartTime());
		assertEquals(expected.getEndTime(), actual.getEndTime());
		assertEquals(expected.getTotalDocCount(), actual.getTotalDocCount());
		assertEquals(expected.getMessage(), actual.getMessage());
	}

	@Test
	void updateWordCollection() {
		// Arrange
		WordCollection collection = createMockData();
		WordCollection savedWordCollection = repository.save(collection);
		int newCollectStatus = CollectStatus.IN_PROGRESS;
		int newAnalysisStatus = AnalysisStatus.IN_PROGRESS;
		LocalDateTime newStartTime = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
		LocalDateTime newEndTime = LocalDateTime.of(2024, 1, 1, 2, 0, 0);
		int newTotalDocCount = 1;
		String newMessage = "test2";
		WordCollection newCollection = new WordCollection(newCollectStatus, newAnalysisStatus, newStartTime,
			newEndTime, newTotalDocCount, newMessage);

		// Act
		savedWordCollection.setCollectStatus(newCollectStatus);
		savedWordCollection.setAnalysisStatus(newAnalysisStatus);
		savedWordCollection.setEndTime(newEndTime);
		savedWordCollection.setTotalDocCount(newTotalDocCount);
		savedWordCollection.setMessage(newMessage);
		WordCollection updatedWordCollection = repository.save(savedWordCollection);

		// Assert
		assertNotNull(updatedWordCollection);
		assertEquals(savedWordCollection.getId(), updatedWordCollection.getId());
		assertDataEquals(newCollection, updatedWordCollection);
	}
}
