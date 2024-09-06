CREATE TABLE `post`
(
    `post_id`       BIGINT   NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `content`       TEXT     NOT NULL,
    `created_at`    DATETIME NOT NULL,
    `collection_id` BIGINT   NOT NULL,
    `api_id`        BIGINT   NOT NULL
);

CREATE TABLE `word_collection`
(
    `collection_id`   BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `collect_status`  INT          NOT NULL DEFAULT 0,
    `analysis_status` INT          NOT NULL DEFAULT 0,
    `start_time`      DATETIME     NOT NULL,
    `end_time`        DATETIME     NULL,
    `total_doc_count` INT          NOT NULL,
    `message`         VARCHAR(255) NULL
);

CREATE TABLE `term_count`
(
    `term_count_id` BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `term`          VARCHAR(255) NOT NULL,
    `term_count`    INT          NOT NULL,
    `post_id`       BIGINT       NOT NULL
);

CREATE TABLE `document_count`
(
    `document_count_id` BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `term`              VARCHAR(255) NOT NULL,
    `doc_count`         INT          NOT NULL,
    `collection_id`     BIGINT       NOT NULL
);

CREATE TABLE `tf_idf_result`
(
    `tfidf_id`    BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `term`        VARCHAR(255) NOT NULL,
    `tf_value`    FLOAT        NOT NULL,
    `idf_value`   FLOAT        NOT NULL,
    `tfidf_value` FLOAT        NOT NULL,
    `post_id`     BIGINT       NOT NULL
);

CREATE TABLE `term_rank`
(
    `rank_id`       BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `term`          VARCHAR(255) NOT NULL,
    `rank_value`    INT          NOT NULL,
    `collection_id` BIGINT       NOT NULL
);