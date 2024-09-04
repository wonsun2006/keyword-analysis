package com.example.keywordanalyzer.learning;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class JdbcLearningTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() {
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY, name VARCHAR(50))");
		jdbcTemplate.update("DELETE FROM users");
		jdbcTemplate.update("INSERT INTO users (id, name) VALUES (?, ?)", 1, "spring");
		jdbcTemplate.update("INSERT INTO users (id, name) VALUES (?, ?)", 2, "jdbc");
	}

	@Test
	void testQueryForObject() {
		String name = jdbcTemplate.queryForObject(
			"SELECT name FROM students WHERE id = ?", new Object[] {1}, String.class);

		assertThat(name).isEqualTo("spring");
	}
}