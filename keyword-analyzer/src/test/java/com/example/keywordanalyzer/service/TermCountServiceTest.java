package com.example.keywordanalyzer.service;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.keywordanalyzer.model.entity.DocumentCount;
import com.example.keywordanalyzer.model.entity.Post;
import com.example.keywordanalyzer.model.entity.TermCount;
import com.example.keywordanalyzer.model.entity.WordCollection;
import com.example.keywordanalyzer.repository.DocumentCountRepository;
import com.example.keywordanalyzer.repository.TermCountRepository;
import com.example.keywordanalyzer.repository.WordCollectionRepository;
import com.example.keywordanalyzer.util.NlpUtil;
import com.example.keywordanalyzer.util.TestUtil;

@ExtendWith(MockitoExtension.class)
public class TermCountServiceTest {
	@Mock
	private WordCollection wordCollection;
	@Mock
	private TermCountRepository termCountRepository;
	@Mock
	private WordCollectionRepository wordCollectionRepository;
	@Mock
	private DocumentCountRepository documentCountRepository;

	@InjectMocks
	private TermCountService service;

	@BeforeEach
	void setUp() {
		Mockito.when(wordCollection.getId()).thenReturn(1L);
		Mockito.when(wordCollectionRepository.existsById(wordCollection.getId())).thenReturn(true);
	}

	@Test
	void saveTermCountWithSinglePost() {
		// Arrange
		Post post = new Post(1L, "Hello World! This test is test post.", LocalDateTime.of(2024, 1, 1, 0, 0, 0),
			wordCollection.getId(), 1L);

		// Act
		service.saveTermCount(post);

		// Assert
		HashMap<String, Integer> actualCounts = NlpUtil.extractNoun(post.getContent());
		HashMap<String, Integer> expectedCounts = new HashMap<>();
		expectedCounts.put("World", 1);
		expectedCounts.put("test", 2);
		expectedCounts.put("post", 1);
		int totalTermCount = expectedCounts.size();

		TestUtil.assertMapEquals(expectedCounts, actualCounts);
		verify(termCountRepository, times(totalTermCount)).save(any(TermCount.class));
		verify(documentCountRepository, times(totalTermCount)).save(any(DocumentCount.class));
	}
}
