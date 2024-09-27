package com.example.keywordanalyzer.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.keywordanalyzer.exception.WordCollectionNotFoundException;
import com.example.keywordanalyzer.model.entity.Document;
import com.example.keywordanalyzer.model.entity.DocumentCount;
import com.example.keywordanalyzer.model.entity.TermCount;
import com.example.keywordanalyzer.repository.DocumentCollectionRepository;
import com.example.keywordanalyzer.repository.DocumentCountRepository;
import com.example.keywordanalyzer.repository.TermCountRepository;
import com.example.keywordanalyzer.util.NlpUtil;

@Service
public class TermCountService {
	private final TermCountRepository termCountRepository;
	private final DocumentCollectionRepository documentCollectionRepository;
	private final DocumentCountRepository documentCountRepository;

	public TermCountService(TermCountRepository repository, DocumentCollectionRepository documentCollectionRepository,
		DocumentCountRepository documentCountRepository) {
		this.termCountRepository = repository;
		this.documentCountRepository = documentCountRepository;
		this.documentCollectionRepository = documentCollectionRepository;
	}

	private void updateTermCount(Long documentId, String term, int count) {
		TermCount termCount = termCountRepository.findByDocumentIdAndTerm(documentId, term)
			.orElse(new TermCount(term, 0, documentId));
		termCount.setTermCount(termCount.getTermCount() + count);
		termCountRepository.save(termCount);
	}

	private void updateDocumentCount(Long documentCollectionId, String term, int count) {
		DocumentCount documentCount = documentCountRepository.findByDocumentCollectionIdAndTerm(documentCollectionId,
				term)
			.orElse(new DocumentCount(term, 0, documentCollectionId));
		documentCount.setDocumentCount(documentCount.getDocumentCount() + count);
		documentCountRepository.save(documentCount);
	}

	@Transactional
	public void saveTermCount(Document document) {
		HashMap<String, Integer> termCountMap = NlpUtil.extractNoun(document.getContent());
		Long documentCollectionId = document.getDocumentCollectionId();
		Long postId = document.getId();

		if (!documentCollectionRepository.existsById(documentCollectionId)) {
			throw new WordCollectionNotFoundException(documentCollectionId);
		}

		termCountMap.forEach((term, count) -> {
			updateTermCount(postId, term, count);
			updateDocumentCount(documentCollectionId, term, count);
		});
	}

}
