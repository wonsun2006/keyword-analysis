package com.example.keywordanalyzer.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.keywordanalyzer.exception.DocumentCollectionNotFoundException;
import com.example.keywordanalyzer.model.entity.Document;
import com.example.keywordanalyzer.model.entity.DocumentCount;
import com.example.keywordanalyzer.model.entity.Term;
import com.example.keywordanalyzer.model.entity.TermCount;
import com.example.keywordanalyzer.repository.DocumentCollectionRepository;
import com.example.keywordanalyzer.repository.DocumentCountRepository;
import com.example.keywordanalyzer.repository.TermCountRepository;
import com.example.keywordanalyzer.repository.TermRepository;
import com.example.keywordanalyzer.util.NlpUtil;

@Service
public class TermCountService {
	private final TermCountRepository termCountRepository;
	private final DocumentCollectionRepository documentCollectionRepository;
	private final DocumentCountRepository documentCountRepository;
	private final TermRepository termRepository;

	public TermCountService(TermCountRepository repository, DocumentCollectionRepository documentCollectionRepository,
		DocumentCountRepository documentCountRepository, TermRepository termRepository) {
		this.termCountRepository = repository;
		this.documentCountRepository = documentCountRepository;
		this.documentCollectionRepository = documentCollectionRepository;
		this.termRepository = termRepository;
	}

	private void updateTermCount(Long documentId, Long termId, int count, Long documentCollectionId) {
		TermCount termCount = termCountRepository.findByDocumentIdAndTermId(documentId, termId)
			.orElse(new TermCount(termId, 0, documentId, documentCollectionId));
		termCount.setTermCount(termCount.getTermCount() + count);
		termCountRepository.save(termCount);
	}

	private void updateDocumentCount(Long documentCollectionId, Long termId, int count) {
		DocumentCount documentCount = documentCountRepository.findByDocumentCollectionIdAndTermId(documentCollectionId,
				termId)
			.orElse(new DocumentCount(termId, 0, documentCollectionId));
		documentCount.setDocumentCount(documentCount.getDocumentCount() + count);
		documentCountRepository.save(documentCount);
	}

	private Term getOrCreateTermEntity(String term) {
		return termRepository.findByValue(term).orElseGet(() -> {
			Term newTermEntity = new Term(term);
			termRepository.save(newTermEntity);
			return newTermEntity;
		});
	}

	@Transactional
	public void saveTermCount(Document document) {
		Map<String, Integer> termCountMap = NlpUtil.extractNoun(document.getContent());
		Long documentCollectionId = document.getDocumentCollectionId();
		Long documentId = document.getDocumentId();

		if (!documentCollectionRepository.existsById(documentCollectionId)) {
			throw new DocumentCollectionNotFoundException(documentCollectionId);
		}

		termCountMap.forEach((term, count) -> {
			Term termEntity = getOrCreateTermEntity(term);
			Long termId = termEntity.getTermId();

			updateTermCount(documentId, termId, count, documentCollectionId);
			updateDocumentCount(documentCollectionId, termId, count);
		});
	}

}
