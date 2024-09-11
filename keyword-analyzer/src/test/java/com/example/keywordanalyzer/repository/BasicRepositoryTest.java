package com.example.keywordanalyzer.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootTest
public abstract class BasicRepositoryTest<T> {
	protected JpaRepository<T, Long> repository;

	public BasicRepositoryTest(JpaRepository<T, Long> repository) {
		this.repository = repository;
	}

	protected abstract Long getId(T entity);

	protected abstract T createMockData();

	protected abstract void assertDataEquals(T expected, T actual);

	@Test
	void repositoryLoads() {
		assertNotNull(repository);
	}

	@Test
	void saveEntity() {
		// Arrange
		T entity = createMockData();

		// Act
		T savedEntity = repository.save(entity);

		// Assert
		assertNotNull(savedEntity);
		assertDataEquals(entity, savedEntity);
	}

	@Test
	void findEntity() {
		// Arrange
		T entity = createMockData();
		T savedEntity = repository.save(entity);

		// Act
		Optional<T> foundEntity = repository.findById(getId(savedEntity));

		// Assert
		assertTrue(foundEntity.isPresent());
		assertEquals(getId(savedEntity), getId(foundEntity.get()));
		assertDataEquals(entity, savedEntity);
	}

	@Test
	void deleteEntity() {
		// Arrange
		T entity = createMockData();
		T savedEntity = repository.save(entity);

		// Act
		repository.deleteById(getId(savedEntity));

		// Assert
		Optional<T> foundPost = repository.findById(getId(savedEntity));
		assertFalse(foundPost.isPresent());
	}
}
