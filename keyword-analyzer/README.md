# 키워드 분석 서버

## 프로젝트 구조

```
keyword-analyzer/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/keyword-analyzer/
│   │   │       ├── KeywordAnalyzerApplication.java
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       ├── model/
│   │   │       ├── config/
│   │   │       └── util/
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       ├── java/
│       │   └── com/example/keyword-analyzer/
│       │       ├── controller/
│       │       ├── service/
│       │       ├── repository/
│       │       └── KeywordAnalyzerApplicationTests.java
│       └── resources/
├── build.gradle
└── README.md
```

## Database 설정

> MySQL에 접속하여 프로젝트에서 사용할 데이터베이스를 생성합니다. 아래 명령을 MySQL 터미널에서 실행하세요
>
> 데이터베이스 이름, 사용자 이름, 비밀번호는 자유롭게 설정하실 수 있습니다.

### 1. 데이터베이스 생성

```mysql
CREATE DATABASE keyword_analysis;
```

### 2. 테이블 생성

> ./src/main/resources/schema.sql 파일을 참고하여 테이블을 생성합니다.

```mysql
CREATE TABLE `posts`
(
    `post_id`       BIGINT   NOT NULL PRIMARY KEY,
    `content`       TEXT     NOT NULL,
    `created_at`    DATETIME NOT NULL,
    `collection_id` BIGINT   NOT NULL,
    `api_id`        BIGINT   NOT NULL
);

CREATE TABLE `collections`
(
    `collection_id`   BIGINT       NOT NULL PRIMARY KEY,
    `collect_status`  INT          NOT NULL DEFAULT 0,
    `analysis_status` INT          NOT NULL DEFAULT 0,
    `start_time`      DATETIME     NOT NULL,
    `end_time`        DATETIME     NOT NULL,
    `total_doc_count` INT          NOT NULL,
    `message`         VARCHAR(255) NULL
);

CREATE TABLE `term_counts`
(
    `tc_id`      BIGINT       NOT NULL PRIMARY KEY,
    `term`       VARCHAR(255) NOT NULL,
    `term_count` INT          NOT NULL,
    `post_id`    BIGINT       NOT NULL
);

CREATE TABLE `document_counts`
(
    `dc_id`         BIGINT       NOT NULL PRIMARY KEY,
    `term`          VARCHAR(255) NOT NULL,
    `doc_count`     INT          NOT NULL,
    `collection_id` BIGINT       NOT NULL
);

CREATE TABLE `tf_idf_results`
(
    `tfidf_id`    BIGINT       NOT NULL PRIMARY KEY,
    `term`        VARCHAR(255) NOT NULL,
    `tf_value`    FLOAT        NOT NULL,
    `idf_value`   FLOAT        NOT NULL,
    `tfidf_value` FLOAT        NOT NULL,
    `post_id`     BIGINT       NOT NULL
);

CREATE TABLE `word_ranks`
(
    `rank_id`       BIGINT       NOT NULL PRIMARY KEY,
    `term`          VARCHAR(255) NOT NULL,
    `rank_value`    INT          NOT NULL,
    `collection_id` BIGINT       NOT NULL
);
```

- 테스트 코드에서 테스트 DB를 사용하려면, 같은 내용으로 DB을 생성합니다.

  테스트 DB 이름: keyword_analysis_test (변경 가능하나, 별도 설정 필요)

### 3. 사용자 생성 및 권한 부여

데이터베이스에 접속할 사용자를 생성하고, 적절한 권한을 부여합니다:

```mysql
CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON keyword_analysis.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

### 4. JDBC 연결 설정

src/main/resources/application.properties 파일에서 데이터베이스 연결 설정을 수정합니다:

```properties
# MySQL 접속 정보
# ./src/main/resources/application-secret.properties
DB_URL=jdbc:mysql://localhost:3306/keyword_analysis
DB_USERNAME=root
DB_PASSWORD=root
```

```properties
# MySQL 접속 정보
# ./src/main/resources/application-test.properties
DB_URL=jdbc:mysql://localhost:3306/keyword_analysis_test
DB_USERNAME=root
DB_PASSWORD=root
```

## 환경변수 설정

### application-secret.properties

> ```/src/main/resources/application-secret.properties``` 파일을 생성하고 아래 내용을 추가합니다.

```properties
# MySQL 접속 정보
DB_URL=jdbc:mysql://localhost:3306/keyword_analysis
DB_USERNAME=root
DB_PASSWORD=root
```