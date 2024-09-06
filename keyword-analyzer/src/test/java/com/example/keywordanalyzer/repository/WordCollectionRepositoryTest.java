package com.example.keywordanalyzer.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.keywordanalyzer.model.constants.AnalysisStatus;
import com.example.keywordanalyzer.model.constants.CollectStatus;
import com.example.keywordanalyzer.model.entity.WordCollection;

@SpringBootTest
public class WordCollectionRepositoryTest {
	@Autowired
	private WordCollectionRepository wordCollectionRepository;

	private WordCollection createMockData() {
		int collectStatus = CollectStatus.NOT_STARTED;
		int analysisStatus = AnalysisStatus.NOT_STARTED;
		LocalDateTime startTime = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
		LocalDateTime endTime = LocalDateTime.of(2024, 1, 1, 1, 0, 0);
		int totalDocCount = 0;
		String message = "test";
		return new WordCollection(collectStatus, analysisStatus, startTime, endTime,
			totalDocCount,
			message);
	}

	private void assertWordCollectionEquals(WordCollection expected, WordCollection actual) {
		assertEquals(expected.getCollectStatus(), actual.getCollectStatus());
		assertEquals(expected.getAnalysisStatus(), actual.getAnalysisStatus());
		assertEquals(expected.getStartTime(), actual.getStartTime());
		assertEquals(expected.getEndTime(), actual.getEndTime());
		assertEquals(expected.getTotalDocCount(), actual.getTotalDocCount());
		assertEquals(expected.getMessage(), actual.getMessage());
	}

	@Test
	void wordCollectionRepositoryLoads() {
		assertThat(wordCollectionRepository).isNotNull();
	}

	@Test
	void saveWordCollection() {
		// Arrange
		WordCollection collection = createMockData();

		// Act
		WordCollection savedWordCollection = wordCollectionRepository.save(collection);

		// Assert
		assertNotNull(savedWordCollection);
		assertWordCollectionEquals(collection, savedWordCollection);
	}

	@Test
	void findWordCollection() {
		// Arrange
		WordCollection collection = createMockData();
		WordCollection savedWordCollection = wordCollectionRepository.save(collection);

		// Act
		Optional<WordCollection> foundWordCollection = wordCollectionRepository.findById(savedWordCollection.getId());

		// Assert
		assertTrue(foundWordCollection.isPresent());
		assertEquals(savedWordCollection.getId(), foundWordCollection.get().getId());
		assertWordCollectionEquals(collection, savedWordCollection);
	}

	@Test
	void updateWordCollection() {
		// Arrange
		WordCollection collection = createMockData();
		WordCollection savedWordCollection = wordCollectionRepository.save(collection);
		int newCollectStatus = CollectStatus.IN_PROGRESS;
		int newAnalysisStatus = AnalysisStatus.IN_PROGRESS;
		LocalDateTime newStartTime = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
		LocalDateTime new_endTime = LocalDateTime.of(2024, 1, 1, 2, 0, 0);
		int newTotalDocCount = 1;
		String newMessage = "test2";
		WordCollection newCollection = new WordCollection(newCollectStatus, newAnalysisStatus, newStartTime,
			new_endTime, newTotalDocCount, newMessage);

		// Act
		savedWordCollection.setCollectStatus(newCollectStatus);
		savedWordCollection.setAnalysisStatus(newAnalysisStatus);
		savedWordCollection.setEndTime(new_endTime);
		savedWordCollection.setTotalDocCount(newTotalDocCount);
		savedWordCollection.setMessage(newMessage);
		WordCollection updatedWordCollection = wordCollectionRepository.save(savedWordCollection);

		// Assert
		assertNotNull(updatedWordCollection);
		assertEquals(savedWordCollection.getId(), updatedWordCollection.getId());
		assertWordCollectionEquals(newCollection, updatedWordCollection);
	}

	@Test
	void deleteWordCollection() {
		// Arrange
		WordCollection collection = createMockData();
		WordCollection savedWordCollection = wordCollectionRepository.save(collection);

		// Act
		wordCollectionRepository.deleteById(savedWordCollection.getId());

		// Assert
		Optional<WordCollection> foundWordCollection = wordCollectionRepository.findById(savedWordCollection.getId());
		assertFalse(foundWordCollection.isPresent());
	}
}
