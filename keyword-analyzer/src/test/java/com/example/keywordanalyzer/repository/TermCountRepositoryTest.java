package com.example.keywordanalyzer.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.keywordanalyzer.model.entity.TermCount;

@SpringBootTest
@Transactional
public class TermCountRepositoryTest extends BasicRepositoryTest<TermCount> {
	@Autowired
	public TermCountRepositoryTest(TermCountRepository repository) {
		super(repository);
	}

	@Override
	protected Long getId(TermCount entity) {
		return entity.getTermCountId();
	}

	@Override
	protected TermCount createMockData() {
		return new TermCount(1L, 1, 1L, 1L);
	}

	@Override
	protected void assertDataEquals(TermCount expected, TermCount actual) {
		assertEquals(expected.getTermId(), actual.getTermId());
		assertEquals(expected.getTermCount(), actual.getTermCount());
		assertEquals(expected.getDocumentId(), actual.getDocumentId());
	}

	@Test
	void updateTermCount() {
		// Arrange
		TermCount tempCount = createMockData();
		TermCount savedTermCount = repository.save(tempCount);
		int newCount = 2;
		TermCount newTermCount = new TermCount(1L, newCount, 1L, 1L);

		// Act
		savedTermCount.setTermCount(newCount);
		TermCount updatedTermCount = repository.save(savedTermCount);

		// Assert
		assertNotNull(updatedTermCount);
		assertEquals(getId(savedTermCount), getId(updatedTermCount));
		assertDataEquals(newTermCount, updatedTermCount);
	}
}
