CREATE TABLE `posts`
(
    `post_id`       BIGINT   NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `content`       TEXT     NOT NULL,
    `created_at`    DATETIME NOT NULL,
    `collection_id` BIGINT   NOT NULL,
    `api_id`        BIGINT   NOT NULL
);

CREATE TABLE `collections`
(
    `collection_id`   BIGINT   NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `collect_status`  INT      NOT NULL DEFAULT 0,
    `analysis_status` INT      NOT NULL DEFAULT 0,
    `start_time`      DATETIME NOT NULL,
    `end_time`        DATETIME NOT NULL,
    `total_doc_count` INT      NOT NULL,
    `message`         VARCHAR(255) NULL
);

CREATE TABLE `term_counts`
(
    `tc_id`      BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `term`       VARCHAR(255) NOT NULL,
    `term_count` INT          NOT NULL,
    `post_id`    BIGINT       NOT NULL
);

CREATE TABLE `document_counts`
(
    `dc_id`         BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `term`          VARCHAR(255) NOT NULL,
    `doc_count`     INT          NOT NULL,
    `collection_id` BIGINT       NOT NULL
);

CREATE TABLE `tf_idf_results`
(
    `tfidf_id`    BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `term`        VARCHAR(255) NOT NULL,
    `tf_value`    FLOAT        NOT NULL,
    `idf_value`   FLOAT        NOT NULL,
    `tfidf_value` FLOAT        NOT NULL,
    `post_id`     BIGINT       NOT NULL
);

CREATE TABLE `term_ranks`
(
    `rank_id`       BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `term`          VARCHAR(255) NOT NULL,
    `rank_value`    INT          NOT NULL,
    `collection_id` BIGINT       NOT NULL
);