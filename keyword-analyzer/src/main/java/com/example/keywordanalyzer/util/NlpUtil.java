package com.example.keywordanalyzer.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.springframework.core.io.ClassPathResource;

import com.example.keywordanalyzer.exception.IllegalNegativeInputException;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;

public class NlpUtil {
	public static HashMap<String, Integer> extractNoun(String text) {
		HashMap<String, Integer> wordCounts = new HashMap<>();

		SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
		String[] tokens = tokenizer.tokenize(text);

		ClassPathResource resource = new ClassPathResource("models/en-pos-maxent.bin");
		try (InputStream modelIn = resource.getInputStream()) {
			POSModel posModel = new POSModel(modelIn);
			POSTaggerME posTagger = new POSTaggerME(posModel);
			String[] posTags = posTagger.tag(tokens);

			// Extract Nouns
			for (int i = 0; i < tokens.length; i++) {
				if (posTags[i].equals("NOUN") || posTags[i].equals("PROPN")) {
					wordCounts.put(tokens[i], wordCounts.getOrDefault(tokens[i], 0) + 1);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("Error reading nlp model file");
		}

		return wordCounts;
	}

	public static double calculateTermFrequency(int termCountInDocument) {
		if (termCountInDocument < 0) {
			throw new IllegalNegativeInputException("termCountInDocument");
		}

		double termFrequency;
		if (termCountInDocument > 0) {
			termFrequency = 1 + Math.log10(termCountInDocument);
		} else {
			termFrequency = 0;
		}
		return termFrequency;
	}

	public static double calculateInverseDocumentFrequency(int totalDocumentCount, int documentCountContainingTerm) {
		if (totalDocumentCount < 0) {
			throw new IllegalNegativeInputException("totalDocumentCount");
		}
		if (documentCountContainingTerm < 0) {
			throw new IllegalNegativeInputException("documentCountContainingTerm");
		}
		if (documentCountContainingTerm > totalDocumentCount) {
			throw new IllegalArgumentException(
				"Document count that contains the term can not be bigger than total document count");
		}

		return Math.log10((double)totalDocumentCount / documentCountContainingTerm);
	}

	public static double calculateTfidf(double tfValue, double idfValue) {
		if (tfValue < 0) {
			throw new IllegalNegativeInputException("tfValue");
		}
		if (idfValue < 0) {
			throw new IllegalNegativeInputException("idfValue");
		}

		return tfValue * idfValue;
	}
}
