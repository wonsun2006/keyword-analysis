package com.example.keywordanalyzer.service;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

	private Post post;

	@BeforeEach
	void setUp() {
		Mockito.when(wordCollection.getId()).thenReturn(1L);
		Mockito.when(wordCollectionRepository.existsById(wordCollection.getId())).thenReturn(true);
		post = new Post("Hello World! This is test post.", LocalDateTime.of(2024, 1, 1, 0, 0, 0),
			wordCollection.getId(), 1L);
	}

	@Test
	void saveTermCountWithPost() {
		// Arrange

		// Act
		service.saveTermCount(post);

		// Assert
		HashMap<String, Integer> actualCounts = NlpUtil.extractNoun(post.getContent());
		HashMap<String, Integer> expectedCounts = new HashMap<>();
		List<String> wordList = Arrays.asList("World", "test", "post");
		for (String word : wordList) {
			expectedCounts.put(word, 1);
		}
		TestUtil.assertMapEquals(expectedCounts, actualCounts);
		verify(termCountRepository, times(wordList.size())).save(any(TermCount.class));
		verify(documentCountRepository, times(wordList.size())).save(any(DocumentCount.class));
	}
}
