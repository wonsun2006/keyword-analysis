package com.example.keywordanalyzer.learning;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {
	@Mock
	List<String> testMockString;

	@Test
	void testBasicMockito() {
		// Mock 객체 생성
		List<?> mockList = Mockito.mock(List.class);

		// Mock 객체의 동작 정의
		Mockito.when(mockList.size()).thenReturn(5);

		// Mock 객체 사용
		int size = mockList.size(); // 5를 반환
		assertEquals(5, size);
	}

	@Test
	void testMockAnnotation() {
		// Mock 동작 정의
		Mockito.when(testMockString.size()).thenReturn(5);

		// Mock 객체 사용
		int size = testMockString.size();
		assertEquals(5, size);
	}
}
