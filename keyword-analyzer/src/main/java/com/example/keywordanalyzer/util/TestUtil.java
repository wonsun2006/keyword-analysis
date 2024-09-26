package com.example.keywordanalyzer.util;

import java.util.Map;

public class TestUtil {
	public static <K, V> boolean assertMapEquals(Map<K, V> expected, Map<K, V> actual) {
		if (expected.size() != actual.size()) {
			return false;
		}

		for (Map.Entry<K, V> entry : expected.entrySet()) {
			K key = entry.getKey();
			V value = entry.getValue();

			if (!actual.containsKey(key)) {
				return false;
			}

			if (!value.equals(actual.get(key))) {
				return false;
			}
		}

		return true;
	}
}
