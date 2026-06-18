# Architecture

마지막 동기화: 2026-06-18

## 1. 개요

현재 구현은 React 18 기반의 기능별 계층 구조를 사용한다. 서브시스템별 코드는 `src/features` 아래에 배치하고, 기본 데이터는 JSON 파일, 실행 중 변경 데이터는 브라우저 `localStorage`에 저장한다.

## 2. 실행 구조

```text
public/index.html
        ↓
src/index.js
        ↓
src/app/App.jsx
        ↓
src/app/menuConfig.js
        ↓
src/features/*/presentation
```

## 3. 계층 구조

```text
presentation → service → domain
                    ↓
              infrastructure
```

- `domain`: 엔티티 생성과 업무 규칙 검증
- `service`: 유스케이스, 중복 ID 및 참조 ID 검사
- `infrastructure`: JSON 초기화와 `localStorage` 접근
- `presentation`: React 화면과 사용자 상호작용

공통 저장 및 검증 코드는 `src/shared`에 둔다. 여러 화면이 사용하는 UI는 `src/components`에 둔다.

## 4. 데이터 흐름

최초 실행 시 Repository가 `public/data/<기능>`의 JSON 배열을 읽고 기능별 저장 키에 초기값을 기록한다. 이후 등록, 조회, 삭제는 service를 거쳐 Repository가 `localStorage`를 갱신한다.

화면은 `localStorage`와 JSON 파일을 직접 접근하지 않는다.

## 5. 기능 구조

```text
src/features/
├─ user/
├─ laboratory/
├─ chemical/
├─ waste/
├─ checklist/
├─ inspection/
└─ education/
```

각 기능은 `domain`, `service`, `infrastructure`, `presentation` 디렉터리를 동일하게 사용한다.

안전교육은 `ResearcherCategory`, `CompletionStandard`, `EducationCourse`, `EducationOpening`, `EducationCompletionResult` 엔티티를 분리하고 ID로 관계를 표현한다.

## 6. 제약

현재 저장 방식은 시연용이다. 데이터는 브라우저별로 분리되며 동시 사용자, 서버 인증, 데이터베이스 외래키 및 감사 이력을 제공하지 않는다.

세부 구현 규칙은 `docs/json-feature-architecture-guide.md`를 따른다.
