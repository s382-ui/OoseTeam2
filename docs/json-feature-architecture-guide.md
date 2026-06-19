# React + Java JSON 계층 구조 구현 가이드

작성일: 2026-06-18

## 목적

팀원이 각 서브시스템을 같은 구조로 구현하기 위한 기준이다. React는 화면과 API 호출만 담당하고, 업무 로직과 JSON 저장은 Java 백엔드가 담당한다.

## 기능별 구조

```text
frontend/src/features/<기능>/
├─ api/
└─ presentation/

backend/src/main/java/com/oose/labsafety/<기능>/
├─ domain/
├─ service/
├─ infrastructure/
└─ presentation/
```

## 프론트엔드 규칙

### api

- Java REST API를 호출한다.
- HTTP 오류 응답의 메시지를 화면에 전달한다.
- 숫자와 boolean처럼 HTML 입력값 변환이 필요한 값만 요청 직전에 변환한다.
- 엔티티 중복 및 참조 관계를 검사하지 않는다.

### presentation

- React 화면, 폼 상태, 검색 조건, 성공·오류 메시지를 관리한다.
- `fetch`를 직접 호출하지 않고 `api` 모듈을 사용한다.
- JSON 파일과 `localStorage`를 직접 사용하지 않는다.

## 백엔드 규칙

### domain

- Java record 또는 클래스로 엔티티를 정의한다.
- 필수값과 숫자 범위는 Jakarta Validation으로 표현한다.
- React와 HTTP에 의존하지 않는다.

### service

- 등록, 조회, 삭제 등 유스케이스를 구현한다.
- ID 중복, 날짜 순서, 참조 엔티티 존재 여부를 검사한다.
- Controller에서 Repository를 직접 호출하지 않도록 한다.

### infrastructure

- Jackson을 이용해 JSON 파일을 읽고 쓴다.
- 초기 JSON은 `src/main/resources/data`에서 읽는다.
- 변경 데이터는 `backend/data`에 저장한다.
- 화면 필드나 React 상태를 알지 못하게 한다.

### presentation

- `@RestController`로 HTTP 요청과 응답을 처리한다.
- 요청 본문에 `@Valid`를 적용한다.
- 업무 판단은 service에 위임한다.

## JSON 규칙

- 최상위 값은 배열로 작성한다.
- 파일 인코딩은 UTF-8을 사용한다.
- 속성명은 camelCase를 사용한다.
- ID와 날짜는 문자열로 저장한다.
- 날짜 형식은 `YYYY-MM-DD`를 사용한다.
- 수량과 시간은 JSON number를 사용한다.
- 여부 값은 새 엔티티에서 boolean 사용을 권장한다.

## 구현 순서

1. SRS와 SDD에서 담당 기능의 엔티티와 유스케이스를 확인한다.
2. `backend/src/main/resources/data/<기능>`에 초기 JSON을 작성한다.
3. Java `domain` 엔티티를 작성한다.
4. Java `infrastructure` Repository를 작성한다.
5. Java `service`에 유스케이스와 검증을 구현한다.
6. Java `presentation`에 REST Controller를 작성한다.
7. React `api` 모듈을 작성한다.
8. React `presentation` 화면을 API에 연결한다.
9. 백엔드와 프론트엔드 빌드를 모두 검증한다.

## API 명명 규칙

```text
GET    /api/<복수자원>       목록 조회
POST   /api/<복수자원>       신규 등록
DELETE /api/<복수자원>/{id}  삭제
```

안전교육처럼 여러 엔티티를 결합한 유스케이스는 업무 의미가 드러나는 경로를 사용한다.

```text
/api/education/researcher-standards
/api/education/completion-results
```

## 팀 작업 점검표

- [ ] React 기능 폴더에는 `api`, `presentation`만 있다.
- [ ] Java 기능 폴더에는 네 계층이 분리되어 있다.
- [ ] React 화면에서 `fetch`, `localStorage`, JSON 파일을 직접 사용하지 않는다.
- [ ] Controller가 Repository를 직접 호출하지 않는다.
- [ ] 중복 ID와 참조 ID를 service에서 검사한다.
- [ ] 초기 JSON이 Java resources에 있다.
- [ ] 실행 데이터 폴더가 Git에서 제외되어 있다.
- [ ] `backend\gradlew.bat test`가 성공한다.
- [ ] `frontend`에서 `npm run build`가 성공한다.
