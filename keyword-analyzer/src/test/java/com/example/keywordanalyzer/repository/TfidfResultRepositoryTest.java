package com.example.keywordanalyzer.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.keywordanalyzer.model.entity.TfidfResult;

@SpringBootTest
public class TfidfResultRepositoryTest extends BasicRepositoryTest<TfidfResult> {
	@Autowired
	public TfidfResultRepositoryTest(TfidfResultRepository repository) {
		super(repository);
	}

	@Override
	protected Long getId(TfidfResult entity) {
		return entity.getId();
	}

	@Override
	protected TfidfResult createMockData() {
		return new TfidfResult(1L, 0.5, 0.3, 0.7, 1L, 1L);
	}

	@Override
	protected void assertDataEquals(TfidfResult expected, TfidfResult actual) {
		assertEquals(expected.getTermId(), actual.getTermId());
		assertEquals(expected.getTfidfValue(), actual.getTfidfValue());
		assertEquals(expected.getDocumentId(), actual.getDocumentId());
	}
}
