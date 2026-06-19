# 연구실 안전관리 시스템 구현 현황

작성일: 2026-06-18

## 프로젝트 개요

현재 프로젝트는 React 프론트엔드와 Java 21 Spring Boot 백엔드로 분리되어 있다.

- React: 화면 렌더링, 폼 상태, 사용자 입력, REST API 호출
- Java: 엔티티, 입력 검증, 유스케이스, 참조 무결성, JSON 저장
- JSON: 기본·시연 데이터와 실행 중 등록 데이터 저장

## 프로젝트 구조

```text
frontend/
├─ public/
└─ src/
   ├─ app/
   ├─ components/
   ├─ features/
   │  └─ <기능>/
   │     ├─ api/
   │     └─ presentation/
   ├─ shared/
   │  ├─ api/
   │  └─ hooks/
   └─ styles/

backend/
├─ src/main/java/com/oose/labsafety/
│  ├─ common/
│  ├─ user/
│  ├─ laboratory/
│  ├─ chemical/
│  ├─ waste/
│  ├─ checklist/
│  ├─ inspection/
│  └─ education/
└─ src/main/resources/data/
```

백엔드의 각 기능은 `domain`, `service`, `infrastructure`, `presentation` 계층을 사용한다. 백엔드 `presentation`은 React 화면이 아니라 REST Controller를 의미한다.

## 요청 흐름

```text
React presentation
        ↓
React api
        ↓ HTTP/JSON
Java REST Controller
        ↓
Java service
        ↓
Java domain / infrastructure
        ↓
JSON 파일
```

## 안전교육 관리

안전교육은 다음 Java 엔티티를 분리해 관리한다.

- `ResearcherCategory`
- `CompletionStandard`
- `EducationCourse`
- `EducationOpening`
- `EducationCompletionResult`

교육이수결과 등록 시 Java 서비스가 사용자 ID와 교육개설정보 ID의 존재 여부를 검사한다.

## 비안전교육 관리

사용자, 연구실, 화학물질, 폐기물, 일상점검, 점검 관리는 SDD 4.2~4.7의 엔티티 필드와 등록·조회 흐름에 맞춰 구현되어 있다.

- 관리별 다중 조건 조회와 상세 정보 표시
- 사용자 CSV 대량 등록
- 연구실 책임자 참조 검증과 위치 중복 검증
- 화학물질 CAS 번호 중복 검증
- 체크리스트 복사와 복합 중복 검증

상세 구현과 데이터 초기화 방법은 [비안전교육 관리 구현](non-education-management.md)을 참조한다.

## 현재 제약

- JSON 파일 기반이므로 여러 서버가 동시에 같은 데이터를 수정하는 환경에는 적합하지 않다.
- 인증, 권한, 감사 로그는 아직 구현되지 않았다.
- 서버 실행 데이터는 로컬 `backend/data`에 저장되므로 팀원 PC 간 자동 공유되지 않는다.
- 학내 데이터베이스 조회, 산업안전보건공단 MSDS API, 실제 첨부파일 저장은 외부 접속 정보와 저장소가 없어 구현되지 않았다.
