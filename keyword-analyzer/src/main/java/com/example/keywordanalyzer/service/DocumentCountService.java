package com.example.keywordanalyzer.service;

import org.springframework.stereotype.Service;

import com.example.keywordanalyzer.model.entity.DocumentCount;
import com.example.keywordanalyzer.repository.DocumentCountRepository;

@Service
public class DocumentCountService {
	private final DocumentCountRepository documentCountRepository;

	public DocumentCountService(DocumentCountRepository documentCountRepository) {
		this.documentCountRepository = documentCountRepository;
	}

	public void updateDocumentCount(DocumentCount documentCount) {
		if (documentCount.getDocumentCount() < 0) {
			throw new IllegalArgumentException("Document count cannot be negative");
		}
		documentCountRepository.save(documentCount);
	}
}
