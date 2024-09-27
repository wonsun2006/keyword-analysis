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
		String term = "Lorem";
		double tfidfValue = 0.5;
		double tfValue = 0.3;
		double idfValue = 0.7;
		Long documentId = 1L;
		return new TfidfResult(term, tfidfValue, tfValue, idfValue, documentId);
	}

	@Override
	protected void assertDataEquals(TfidfResult expected, TfidfResult actual) {
		assertEquals(expected.getTerm(), actual.getTerm());
		assertEquals(expected.getTfidfValue(), actual.getTfidfValue());
		assertEquals(expected.getDocumentId(), actual.getDocumentId());
	}
}
