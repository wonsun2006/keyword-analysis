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
		String term = "Lorem";
		int rankValue = 1;
		Long documentCollectionId = 1L;
		return new TermRank(term, rankValue, documentCollectionId);
	}

	@Override
	protected void assertDataEquals(TermRank expected, TermRank actual) {
		assertEquals(expected.getTerm(), actual.getTerm());
		assertEquals(expected.getRankValue(), actual.getRankValue());
		assertEquals(expected.getDocumentCollectionId(), actual.getDocumentCollectionId());
	}
}
