# Lab Safety Management Program

## 문서 동기화 정보

- 마지막 동기화 일자: 2026-05-29
- 기준 문서: `reference_mdFileList/SDD_V1_0_0528.md`
- 반영 범위: 콘솔 입력 필드와 모듈 설명 문서

## 개정 이력

| 버전   | 일자       | 변경 내용                                          |
| ------ | ---------- | -------------------------------------------------- |
| v1.0.0 | 2026-05-29 | README 초안 작성                                   |
| v1.1.0 | 2026-05-29 | SDD 기준 반영 현황 및 실행 제약(Wrapper 부재) 반영 |

`program` 폴더는 연구실 안전관리 시스템의 Java 21 구현체다. 현재 구현은 콘솔 기반 모듈형 모놀리식 구조이며, 기능은 `user`, `laboratory`, `chemical`, `waste`, `inspection`, `education` 모듈로 분리한다.

최신 기준 문서는 `reference_mdFileList/SDD_V1_0_0528.md`이며, 현재 코드의 도메인 입력 항목도 해당 SDD 명세를 기준으로 정렬했다.

## SDD 반영 현황 (요약)

- 사용자 관리: 연락처(`contact`), 계정상태(`accountStatus`), 등록일시(`registeredAt`) 반영
- 연구실 관리: 건물/층/호실, 소속, 책임자 ID, 관리등급, 사용여부, 등록일시 반영
- 화학물질 관리: 제조사, CAS 번호, 함유량, MSDS/성분분석표 경로, 상태, 등록일시 반영
- 폐기물 관리: 폐기물 분류(`WasteCategory`) 중심 필드 반영
- 점검 관리: 점검분야 분류(`InspectionCategory`) 중심 필드 반영
- 안전교육 관리: 교육이수결과(`EducationCompletionResult`) 중심 필드 반영
- 공통 입력: `yyyy-MM-ddTHH:mm` 형식의 DateTime 입력 지원

## 핵심 참고 문서

- [폴더 맵](docs/folder-map.md)
- [핵심 아키텍처](docs/core-architecture.md)
- [동작 흐름](docs/operation-flow.md)
- [모듈 가이드](docs/module-guide.md)
- [실행 가이드](docs/run-guide.md)

## 유지보수 원칙

- 화면은 `presentation`에서만 다룬다.
- 유스케이스 흐름은 `application`에서만 조립한다.
- 상태와 규칙은 `domain`에서 유지한다.
- 저장과 조회는 `infrastructure`에 둔다.
- 공통 기능은 `common`에 모은다.

## 참고

- 현재 `program` 루트에는 `gradlew.bat`가 없으므로, 실행/빌드는 로컬 `gradle` 설치 또는 `javac` 수동 실행이 필요하다.
