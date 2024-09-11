package com.example.keywordanalyzer.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.keywordanalyzer.model.entity.TermCount;

@SpringBootTest
public class TermCountRepositoryTest extends BasicRepositoryTest<TermCount> {
	@Autowired
	public TermCountRepositoryTest(TermCountRepository repository) {
		super(repository);
	}

	@Override
	protected Long getId(TermCount entity) {
		return entity.getId();
	}

	@Override
	protected TermCount createMockData() {
		String term = "Lorem";
		int count = 1;
		Long postId = 1L;
		return new TermCount(term, count, postId);
	}

	@Override
	protected void assertDataEquals(TermCount expected, TermCount actual) {
		assertEquals(expected.getTerm(), actual.getTerm());
		assertEquals(expected.getTermCount(), actual.getTermCount());
		assertEquals(expected.getPostId(), actual.getPostId());
	}

	@Test
	void updateTermCount() {
		// Arrange
		TermCount tempCount = createMockData();
		TermCount savedTermCount = repository.save(tempCount);
		int newCount = 2;
		TermCount newTermCount = new TermCount("Lorem", newCount, 1L);

		// Act
		savedTermCount.setTermCount(newCount);
		TermCount updatedTermCount = repository.save(savedTermCount);

		// Assert
		assertNotNull(updatedTermCount);
		assertEquals(getId(savedTermCount), getId(updatedTermCount));
		assertDataEquals(newTermCount, updatedTermCount);
	}
}
