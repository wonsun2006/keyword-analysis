package com.example.keywordanalyzer.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;

@SpringBootTest
public class JpaConfigurationTest {

	@Autowired
	private EntityManager entityManager;

	@Test
	void entityManagerLoads() {
		assertThat(entityManager).isNotNull();

		Object result = entityManager.createNativeQuery("SELECT 1").getSingleResult();
		assertThat(result).isEqualTo(1L);
	}
}