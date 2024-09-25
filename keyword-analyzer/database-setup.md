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

[shema.sql](https://github.com/wonsun2006/keyword-analysis/blob/master/keyword-analyzer/src/main/resources/db/schema.sql)

- 테스트 코드에서 테스트 DB를 사용하려면, 같은 내용으로 DB을 생성합니다.

  테스트 DB 이름: keyword_analysis_test (변경 가능하나, 별도 설정 필요)

### 3. 사용자 생성 및 권한 부여

데이터베이스에 접속할 사용자를 생성하고, 적절한 권한을 부여합니다.

```mysql
CREATE USER '유저이름'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON keyword_analysis.* TO '유저이름'@'localhost';
FLUSH PRIVILEGES;
```

### 4. JDBC 연결 설정

src/main/resources/application.properties 파일에서 데이터베이스 연결 설정을 수정합니다.

```properties
# MySQL 접속 정보
# ./src/main/resources/application-prod.properties
DB_URL=jdbc:mysql://localhost:3306/keyword_analysis
DB_USERNAME=유저이름
DB_PASSWORD=비밀번호
```

```properties
# MySQL 접속 정보
# ./src/main/resources/application-test.properties
DB_URL=jdbc:mysql://localhost:3306/keyword_analysis_test
DB_USERNAME=유저이름
DB_PASSWORD=비밀번호
```