package com.example.keywordanalyzer.util;

import static java.lang.Math.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.example.keywordanalyzer.model.constants.AnalysisStatus;
import com.example.keywordanalyzer.model.constants.CollectStatus;
import com.example.keywordanalyzer.model.entity.Document;
import com.example.keywordanalyzer.model.entity.DocumentCollection;
import com.example.keywordanalyzer.model.entity.DocumentCount;
import com.example.keywordanalyzer.model.entity.Term;
import com.example.keywordanalyzer.model.entity.TermCount;
import com.example.keywordanalyzer.model.entity.TfidfResult;

public class TestDataGenerator {
	public static DocumentCollection generateDocumentCollection() {
		return new DocumentCollection(1L, CollectStatus.NOT_STARTED, AnalysisStatus.NOT_STARTED,
			LocalDateTime.of(2024, 1, 1, 0, 0, 0),
			LocalDateTime.of(2024, 1, 1, 0, 0, 0), 2, "test collection");
	}

	public static List<Term> generateTerms() {
		return Arrays.asList(
			new Term(1L, "Sample"),
			new Term(2L, "test"),
			new Term(3L, "content"),
			new Term(4L, "example")
		);
	}

	public static List<Document> generateDocuments() {
		return Arrays.asList(
			new Document(1L, "Sample test content one for test", LocalDateTime.of(2024, 1, 1, 0, 0, 0), 1L, 1L),
			new Document(2L, "Sample test content two for example", LocalDateTime.of(2024, 1, 1, 0, 0, 0), 1L, 2L)
		);
	}

	public static List<TermCount> generateTermCounts() {
		return Arrays.asList(
			new TermCount(1L, 1, 1L, 1L),
			new TermCount(2L, 2, 1L, 1L),
			new TermCount(3L, 1, 1L, 1L),
			new TermCount(1L, 2, 2L, 1L),
			new TermCount(2L, 1, 2L, 1L),
			new TermCount(3L, 1, 2L, 1L),
			new TermCount(4L, 1, 2L, 1L)
		);
	}

	public static List<DocumentCount> generateDocumentCounts() {
		return Arrays.asList(
			new DocumentCount(1L, 2, 1L),
			new DocumentCount(2L, 2, 1L),
			new DocumentCount(3L, 2, 1L),
			new DocumentCount(4L, 1, 1L)
		);
	}

	public static List<TfidfResult> generateTfidfResults() {
		return Arrays.asList(
			new TfidfResult(1L, 0, 1L, 0, 1L, 1L),
			new TfidfResult(2L, 0, 1L + log10(2), 0, 1L, 1L),
			new TfidfResult(3L, 0, 1L, 0, 1L, 1L),
			new TfidfResult(1L, 0, 1L + log10(2), 0, 2L, 1L),
			new TfidfResult(2L, 0, 1L, 0, 2L, 1L),
			new TfidfResult(3L, 0, 1L, 0, 2L, 1L),
			new TfidfResult(4L, log10(2), 1L, log10(2), 2L, 1L)
		);
	}
}
