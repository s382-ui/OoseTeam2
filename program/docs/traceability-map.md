# Traceability Map

## 문서 동기화 정보

- 마지막 동기화 일자: 2026-05-29
- 기준 문서: `../../reference_mdFileList/SRS_V1_0_0528.md`, `../../reference_mdFileList/SDD_V1_0_0528.md`
- 반영 범위: SRS 기능 요구사항, SDD 설계 요소, Java 패키지, 실행 문서 간 연결

## 개정 이력

| 버전   | 일자       | 변경 내용                         |
| ------ | ---------- | --------------------------------- |
| v1.0.0 | 2026-05-29 | 요구사항-설계-코드 연결 기준 추가 |
| v1.1.0 | 2026-05-29 | 공통 수정 흐름 및 CRUD 반영 범위 갱신 |

## 1. 기준 문서 연결

| 구분 | 문서 | 역할 |
| ---- | ---- | ---- |
| 요구사항 | `../../reference_mdFileList/SRS_V1_0_0528.md` | 기능 요구사항과 유스케이스 기준 |
| 설계 | `../../reference_mdFileList/SDD_V1_0_0528.md` | Boundary, Control, Entity, Table 기준 |
| 구현 아키텍처 | `../../reference_mdFileList/architecture.md` | 구현 계층과 패키지 분리 기준 |
| 실행 | `run-guide.md` | 빌드와 콘솔 실행 절차 |

## 2. 기능 추적표

| SRS 기능 | SDD 기준 | 구현 패키지 | 현재 콘솔 반영 범위 |
| -------- | -------- | ----------- | ------------------- |
| SFR-004 사용자 관리 | User, UserManagementService, UserRepository | `user` | 등록, 목록, 키워드 검색, 상세 조회, 식별자 수정, 식별자 삭제 |
| SFR-002 연구실 정보 관리 | Laboratory, LaboratoryManagementControl | `laboratory` | 등록, 목록, 키워드 검색, 상세 조회, 식별자 수정, 식별자 삭제 |
| SFR-008 화학물질 관리 | ChemicalMaster, ChemicalManagementService | `chemical` | 등록, 목록, 키워드 검색, 상세 조회, 식별자 수정, 식별자 삭제 |
| SFR-009 폐기물 관리 | WasteCategory, WasteManagementService | `waste` | 등록, 목록, 키워드 검색, 상세 조회, 식별자 수정, 식별자 삭제 |
| SFR-005/SFR-006 점검 관리 | InspectionCategory, ChecklistItem | `inspection` | 점검분야 분류 등록, 목록, 키워드 검색, 상세 조회, 식별자 수정, 식별자 삭제 |
| SFR-015 안전교육 관리 | EducationCompletionResult, Certificate | `education` | 교육이수결과 등록, 목록, 키워드 검색, 상세 조회, 식별자 수정, 식별자 삭제 |

## 3. 계층 매핑

| SDD 요소 | Java 계층 | 대표 파일 |
| -------- | --------- | --------- |
| Boundary/View | `presentation`, `ui` | `*Menu.java`, `MainMenu.java` |
| Control/Service | `service` | `*Service.java` |
| Entity | `domain` | `*Record.java`, `*Profile.java`, `*Item.java` |
| Repository/DB Access | `infrastructure` | `*Repository.java` |
| 공통 규약 | `common` | `AbstractCatalogModule.java`, `AbstractCatalogService.java` |

## 4. 유지보수 순서

1. SRS 기능 요구사항이 바뀌면 이 문서의 기능 추적표를 먼저 갱신한다.
2. SDD Entity 필드가 바뀌면 `domain`, `presentation`, `module-guide.md`를 함께 갱신한다.
3. 메뉴 기능이 바뀌면 `AbstractCatalogModule`, `operation-flow.md`, `run-guide.md`를 함께 갱신한다.
4. 새 모듈을 추가하면 `ApplicationBootstrap`, `folder-map.md`, `core-architecture.md`를 함께 갱신한다.
