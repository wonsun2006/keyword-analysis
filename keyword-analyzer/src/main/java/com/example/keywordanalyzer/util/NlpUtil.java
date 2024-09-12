package com.example.keywordanalyzer.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.springframework.core.io.ClassPathResource;

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
}
