-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: keyword_analysis_test
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document`
(
    `document_id`            bigint      NOT NULL AUTO_INCREMENT,
    `api_id`                 bigint      NOT NULL,
    `content`                text        NOT NULL,
    `created_at`             datetime(6) NOT NULL,
    `document_collection_id` bigint      NOT NULL,
    PRIMARY KEY (`document_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `document_collection`
--

DROP TABLE IF EXISTS `document_collection`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document_collection`
(
    `document_collection_id` bigint       NOT NULL AUTO_INCREMENT,
    `analysis_status`        int          NOT NULL,
    `collect_status`         int          NOT NULL,
    `end_time`               datetime(6) DEFAULT NULL,
    `message`                varchar(255) NOT NULL,
    `start_time`             datetime(6)  NOT NULL,
    `total_document_count`   int          NOT NULL,
    PRIMARY KEY (`document_collection_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `document_count`
--

DROP TABLE IF EXISTS `document_count`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document_count`
(
    `document_count_id`      bigint NOT NULL AUTO_INCREMENT,
    `document_collection_id` bigint NOT NULL,
    `document_count`         int    NOT NULL DEFAULT '0',
    `term_id`                bigint NOT NULL,
    PRIMARY KEY (`document_count_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `term`
--

DROP TABLE IF EXISTS `term`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `term`
(
    `term_id` bigint       NOT NULL AUTO_INCREMENT,
    `value`   varchar(255) NOT NULL,
    PRIMARY KEY (`term_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `term_count`
--

DROP TABLE IF EXISTS `term_count`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `term_count`
(
    `term_count_id`          bigint NOT NULL AUTO_INCREMENT,
    `document_collection_id` bigint NOT NULL,
    `document_id`            bigint NOT NULL,
    `term_count`             int    NOT NULL DEFAULT '0',
    `term_id`                bigint NOT NULL,
    PRIMARY KEY (`term_count_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `term_rank`
--

DROP TABLE IF EXISTS `term_rank`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `term_rank`
(
    `term_rank_id`           bigint NOT NULL AUTO_INCREMENT,
    `document_collection_id` bigint NOT NULL,
    `rank_value`             int    NOT NULL,
    `term_id`                bigint NOT NULL,
    PRIMARY KEY (`term_rank_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tfidf_result`
--

DROP TABLE IF EXISTS `tfidf_result`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tfidf_result`
(
    `tfidf_result_id`        bigint NOT NULL AUTO_INCREMENT,
    `document_collection_id` bigint NOT NULL,
    `document_id`            bigint NOT NULL,
    `idf_value`              double NOT NULL,
    `term_id`                bigint NOT NULL,
    `tf_value`               double NOT NULL,
    `tfidf_value`            double NOT NULL,
    PRIMARY KEY (`tfidf_result_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2024-09-27 21:57:16
