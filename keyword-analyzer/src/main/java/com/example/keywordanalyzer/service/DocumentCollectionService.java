package com.example.keywordanalyzer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.keywordanalyzer.exception.DocumentCollectionNotFoundException;
import com.example.keywordanalyzer.exception.TermIdNotFoundInIdfResult;
import com.example.keywordanalyzer.model.entity.DocumentCollection;
import com.example.keywordanalyzer.model.entity.DocumentCount;
import com.example.keywordanalyzer.model.entity.TermCount;
import com.example.keywordanalyzer.model.entity.TfidfResult;
import com.example.keywordanalyzer.repository.DocumentCollectionRepository;
import com.example.keywordanalyzer.repository.DocumentCountRepository;
import com.example.keywordanalyzer.repository.TermCountRepository;
import com.example.keywordanalyzer.repository.TfidfResultRepository;
import com.example.keywordanalyzer.util.NlpUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DocumentCollectionService {
	private final DocumentCountRepository documentCountRepository;
	private final TermCountRepository termCountRepository;
	private final DocumentCollectionRepository documentCollectionRepository;
	private final TfidfResultRepository tfidfResultRepository;

	Map<Long, Double> calculateIdfResultMap(Integer totalDocumentCount, List<DocumentCount> documentCountlist) {
		Map<Long, Double> idfResultMap = new HashMap<>();
		for (DocumentCount documentCount : documentCountlist) {
			double idfValue = NlpUtil.calculateInverseDocumentFrequency(totalDocumentCount,
				documentCount.getDocumentCount());
			idfResultMap.put(documentCount.getTermId(), idfValue);
		}

		return idfResultMap;
	}

	List<TfidfResult> saveTfidfResultWithTermCountList(List<TermCount> termCountList, Map<Long, Double> idfResultMap,
		Long documentCollectionId) {
		List<TfidfResult> tfidfResultList = new ArrayList<>();
		for (TermCount termCount : termCountList) {
			if (!idfResultMap.containsKey(termCount.getTermId())) {
				throw new TermIdNotFoundInIdfResult(termCount.getTermId());
			}
			double tfValue = NlpUtil.calculateTermFrequency(termCount.getTermCount());
			double idfValue = idfResultMap.get(termCount.getTermId());
			double tfidfValue = NlpUtil.calculateTfidf(tfValue, idfValue);
			TfidfResult tfidfResult = new TfidfResult(termCount.getTermId(), tfidfValue, tfValue, idfValue,
				termCount.getDocumentId(), documentCollectionId);
			TfidfResult ret = tfidfResultRepository.save(tfidfResult);
			tfidfResultList.add(ret);
		}
		return tfidfResultList;
	}

	List<TfidfResult> analyzeDocumentCollection(Long documentCollectionId) {
		DocumentCollection documentCollection = documentCollectionRepository.findById(documentCollectionId).orElseThrow(
			() -> new DocumentCollectionNotFoundException(documentCollectionId)
		);
		List<DocumentCount> documentCountList = documentCountRepository.findAllByDocumentCollectionId(
			documentCollectionId);
		Map<Long, Double> idfResultMap = calculateIdfResultMap(documentCollection.getTotalDocumentCount(),
			documentCountList);

		List<TermCount> termCountList = termCountRepository.findAllByDocumentCollectionId(documentCollectionId);

		List<TfidfResult> tfidfResultList = saveTfidfResultWithTermCountList(termCountList, idfResultMap,
			documentCollectionId);

		return tfidfResultList;
	}
}
