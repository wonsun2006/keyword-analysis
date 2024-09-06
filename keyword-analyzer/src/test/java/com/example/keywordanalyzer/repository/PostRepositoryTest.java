package com.example.keywordanalyzer.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.keywordanalyzer.model.entity.Post;

@SpringBootTest
public class PostRepositoryTest {
	@Autowired
	private PostRepository postCollectionRepository;

	private Post createMockData() {
		String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
		LocalDateTime createdAt = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
		Long collectionId = 1L;
		Long apiId = 1L;
		return new Post(content, createdAt, collectionId, apiId);
	}

	private void assertPostEquals(Post expected, Post actual) {
		assertEquals(expected.getContent(), actual.getContent());
		assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
		assertEquals(expected.getCollectionId(), actual.getCollectionId());
		assertEquals(expected.getApiId(), actual.getApiId());
	}

	@Test
	void postCollectionRepositoryLoads() {
		assertThat(postCollectionRepository).isNotNull();
	}

	@Test
	void savePost() {
		// Arrange
		Post post = createMockData();

		// Act
		Post savedPost = postCollectionRepository.save(post);

		// Assert
		assertNotNull(savedPost);
		assertPostEquals(post, savedPost);
	}

	@Test
	void findPost() {
		// Arrange
		Post post = createMockData();
		Post savedPost = postCollectionRepository.save(post);

		// Act
		Optional<Post> foundPost = postCollectionRepository.findById(savedPost.getId());

		// Assert
		assertTrue(foundPost.isPresent());
		assertEquals(savedPost.getId(), foundPost.get().getId());
		assertPostEquals(post, savedPost);
	}

	@Test
	void deletePost() {
		// Arrange
		Post post = createMockData();
		Post savedPost = postCollectionRepository.save(post);

		// Act
		postCollectionRepository.deleteById(savedPost.getId());

		// Assert
		Optional<Post> foundPost = postCollectionRepository.findById(savedPost.getId());
		assertFalse(foundPost.isPresent());
	}
}
