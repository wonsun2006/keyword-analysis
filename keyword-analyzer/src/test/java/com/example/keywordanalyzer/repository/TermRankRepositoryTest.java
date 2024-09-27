package com.example.keywordanalyzer.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.keywordanalyzer.model.entity.TermRank;

@SpringBootTest
public class TermRankRepositoryTest extends BasicRepositoryTest<TermRank> {
	@Autowired
	public TermRankRepositoryTest(TermRankRepository repository) {
		super(repository);
	}

	@Override
	protected Long getId(TermRank entity) {
		return entity.getId();
	}

	@Override
	protected TermRank createMockData() {
		return new TermRank(1L, 1, 1L);
	}

	@Override
	protected void assertDataEquals(TermRank expected, TermRank actual) {
		assertEquals(expected.getTermId(), actual.getTermId());
		assertEquals(expected.getRankValue(), actual.getRankValue());
		assertEquals(expected.getDocumentCollectionId(), actual.getDocumentCollectionId());
	}
}
