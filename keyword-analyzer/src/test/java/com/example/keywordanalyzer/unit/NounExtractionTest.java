package com.example.keywordanalyzer.unit;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.example.keywordanalyzer.util.NlpUtil;

public class NounExtractionTest {
	protected String getMockData() {
		return "This summer, I embarked on a side project to create a brand-new Palm OS game, "
			+ "and after less than two months of intermittent coding, I'm excited to announce "
			+ "that it's ready to be released to the public!";
	}

	protected HashMap<String, Integer> getMockDataResult() {
		HashMap<String, Integer> wordCounts = new HashMap<>();
		wordCounts.put("summer", 1);
		wordCounts.put("side", 1);
		wordCounts.put("project", 1);
		wordCounts.put("game", 1);
		wordCounts.put("months", 1);
		wordCounts.put("coding", 1);
		wordCounts.put("public", 1);
		wordCounts.put("Palm", 1);
		wordCounts.put("OS", 1);
		wordCounts.put("m", 1);
		wordCounts.put("brand", 1);

		return wordCounts;
	}

	@Test
	void NounExtractionSuccess() {
		// Arrange
		String plainText = getMockData();

		// Act
		HashMap<String, Integer> expected = NlpUtil.extractNoun(plainText);
		HashMap<String, Integer> actual = getMockDataResult();

		// Assert
		assertNotNull(expected);
		for (String key : actual.keySet()) {
			assertThat(expected).containsKey(key);
			assertThat(expected.get(key)).isEqualTo(actual.get(key));
		}
	}

	@Test
	void EmptyTextInput() {
		// Arrange
		String emptyText = "";

		// Act
		HashMap<String, Integer> expected = NlpUtil.extractNoun(emptyText);

		// Assert
		assertNotNull(expected);
		assertThat(expected).isEmpty();
	}
}
