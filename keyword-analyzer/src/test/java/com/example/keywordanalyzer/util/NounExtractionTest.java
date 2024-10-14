package com.example.keywordanalyzer.util;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class NounExtractionTest {
	protected List<String> generateContents() {
		return Arrays.asList(
			"This summer, I embarked on a side project to create a brand-new Palm OS game, "
				+ "and after less than two months of intermittent coding, I'm excited to announce "
				+ "that it's ready to be released to the public!",
			"Sample test content one for test",
			"Sample test content two for example"
		);

	}

	protected List<Map<String, Integer>> generateResultList() {
		List<Map<String, Integer>> result = new ArrayList<>();
		Map<String, Integer> termCountResult1 = new HashMap<>();
		termCountResult1.put("summer", 1);
		termCountResult1.put("side", 1);
		termCountResult1.put("project", 1);
		termCountResult1.put("game", 1);
		termCountResult1.put("months", 1);
		termCountResult1.put("coding", 1);
		termCountResult1.put("public", 1);
		termCountResult1.put("Palm", 1);
		termCountResult1.put("OS", 1);
		termCountResult1.put("m", 1);
		termCountResult1.put("brand", 1);
		result.add(termCountResult1);

		Map<String, Integer> termCountResult2 = new HashMap<>();
		termCountResult2.put("Sample", 1);
		termCountResult2.put("test", 2);
		termCountResult2.put("content", 1);
		result.add(termCountResult2);

		Map<String, Integer> termCountResult3 = new HashMap<>();
		termCountResult3.put("Sample", 1);
		termCountResult3.put("test", 1);
		termCountResult3.put("content", 1);
		termCountResult3.put("example", 1);
		result.add(termCountResult3);

		return result;
	}

	@Test
	void NounExtractionSuccess() {
		// Arrange
		List<String> plainTextList = generateContents();
		List<Map<String, Integer>> actualResultList = generateResultList();

		for (int i = 0; i < plainTextList.size(); i++) {
			String plainText = plainTextList.get(i);

			// Act
			Map<String, Integer> expected = NlpUtil.extractNoun(plainText);
			Map<String, Integer> actual = actualResultList.get(i);

			// Assert
			assertNotNull(expected);
			for (String key : actual.keySet()) {
				assertThat(expected).containsKey(key);
				assertThat(expected.get(key)).isEqualTo(actual.get(key));
			}
		}
	}

	@Test
	void EmptyTextInput() {
		// Arrange
		String emptyText = "";

		// Act
		Map<String, Integer> expected = NlpUtil.extractNoun(emptyText);

		// Assert
		assertNotNull(expected);
		assertThat(expected).isEmpty();
	}
}
