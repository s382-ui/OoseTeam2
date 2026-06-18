# 연구실 안전관리 시스템 구현 현황

작성일: 2026-06-18

## 프로젝트 개요

이 프로젝트는 React 18과 Create React App을 사용하는 연구실 안전관리 시스템이다. 각 업무 기능을 `domain`, `service`, `infrastructure`, `presentation` 계층으로 분리했으며, 서버 데이터베이스 대신 초기 JSON 데이터와 브라우저 `localStorage`를 사용한다.

## 현재 구조

```text
src/
├─ app/
├─ components/
├─ features/
│  ├─ user/
│  ├─ laboratory/
│  ├─ chemical/
│  ├─ waste/
│  ├─ checklist/
│  ├─ inspection/
│  └─ education/
├─ shared/
└─ styles/
```

각 `features/<기능>` 폴더는 다음 계층을 가진다.

- `domain`: 엔티티 생성과 입력값 검증
- `service`: 등록, 조회, 삭제 및 참조 무결성 검사
- `infrastructure`: 초기 JSON 로딩과 `localStorage` Repository
- `presentation`: React 화면과 사용자 입력 처리

## 데이터 저장

- 기본 및 시연 데이터: `public/data/<기능>/*.json`
- 실행 중 등록 데이터: 브라우저 `localStorage`
- 저장 키 형식: `oose.<기능>.<엔티티>`

JSON 파일은 초기 데이터이며 브라우저에서 직접 수정하지 않는다. 해당 저장 키가 없는 최초 실행에 JSON을 읽어 `localStorage`를 초기화한다.

## 안전교육 관리

안전교육 관리는 다음 네 가지 유스케이스를 제공한다.

1. 연구활동종사자 및 이수기준 설정 등록
2. 연구활동종사자 및 이수기준 설정 조회
3. 교육이수 결과 등록
4. 교육이수 내역 조회

`ResearcherCategory`, `CompletionStandard`, `EducationCourse`, `EducationOpening`, `EducationCompletionResult`를 별도 엔티티로 관리한다. 서비스 계층에서 분류, 사용자, 교육개설정보 등의 참조 ID 존재 여부를 검사한다.

## 현재 제약

- 데이터는 사용자 브라우저에만 저장되며 다른 PC와 공유되지 않는다.
- 브라우저 저장소를 삭제하면 사용자가 입력한 데이터가 사라진다.
- 여러 사용자의 동시 수정과 서버 수준 외래키는 지원하지 않는다.
- 자동화 테스트는 아직 구성되지 않았다.

## 구현 기준

팀원이 기능을 추가하거나 변경할 때는 `docs/json-feature-architecture-guide.md`를 따른다. 화면에서 `localStorage`를 직접 사용하지 않고 반드시 service와 repository를 거쳐야 한다.
