package com.example.keywordanalyzer.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class JdbcConfigurationTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void jdbcTemplateLoads() {
		// JDBC 설정이 제대로 되었는지 확인
		assertThat(jdbcTemplate).isNotNull();

		// 간단한 쿼리 실행
		Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
		assertThat(result).isEqualTo(1);
	}
}
