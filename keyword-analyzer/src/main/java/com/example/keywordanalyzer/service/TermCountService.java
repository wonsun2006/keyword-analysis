package com.example.keywordanalyzer.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.keywordanalyzer.exception.WordCollectionNotFoundException;
import com.example.keywordanalyzer.model.entity.DocumentCount;
import com.example.keywordanalyzer.model.entity.Post;
import com.example.keywordanalyzer.model.entity.TermCount;
import com.example.keywordanalyzer.repository.DocumentCountRepository;
import com.example.keywordanalyzer.repository.TermCountRepository;
import com.example.keywordanalyzer.repository.WordCollectionRepository;
import com.example.keywordanalyzer.util.NlpUtil;

@Service
public class TermCountService {
	private final TermCountRepository termCountRepository;
	private final WordCollectionRepository wordCollectionRepository;
	private final DocumentCountRepository documentCountRepository;

	public TermCountService(TermCountRepository repository, WordCollectionRepository wordCollectionRepository,
		DocumentCountRepository documentCountRepository) {
		this.termCountRepository = repository;
		this.documentCountRepository = documentCountRepository;
		this.wordCollectionRepository = wordCollectionRepository;
	}

	private void updateTermCount(Long postId, String term, int count) {
		TermCount termCount = termCountRepository.findByPostIdAndTerm(postId, term)
			.orElse(new TermCount(term, 0, postId));
		termCount.setTermCount(termCount.getTermCount() + count);
		termCountRepository.save(termCount);
	}

	private void updateDocumentCount(Long wordCollectionId, String term, int count) {
		DocumentCount documentCount = documentCountRepository.findByCollectionIdAndTerm(wordCollectionId, term)
			.orElse(new DocumentCount(term, 0, wordCollectionId));
		documentCount.setDocumentCount(documentCount.getDocumentCount() + count);
		documentCountRepository.save(documentCount);
	}

	@Transactional
	public void saveTermCount(Post post) {
		HashMap<String, Integer> termCountMap = NlpUtil.extractNoun(post.getContent());
		Long wordCollectionId = post.getCollectionId();
		Long postId = post.getId();

		if (!wordCollectionRepository.existsById(wordCollectionId)) {
			throw new WordCollectionNotFoundException(wordCollectionId);
		}

		termCountMap.forEach((term, count) -> {
			updateTermCount(postId, term, count);
			updateDocumentCount(wordCollectionId, term, count);
		});
	}

}
