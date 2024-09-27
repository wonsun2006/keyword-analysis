package com.example.keywordanalyzer.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.keywordanalyzer.model.entity.Term;

@SpringBootTest
@Transactional
public class TermRepositoryTest extends BasicRepositoryTest<Term> {
	@Autowired
	public TermRepositoryTest(TermRepository repository) {
		super(repository);
	}

	@Override
	protected Long getId(Term entity) {
		return entity.getTermId();
	}

	@Override
	protected Term createMockData() {
		return new Term(1L, "Lorem");
	}

	@Override
	protected void assertDataEquals(Term expected, Term actual) {
		assertEquals(expected.getValue(), actual.getValue());
	}
}