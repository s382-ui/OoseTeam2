# Folder Map

## 문서 동기화 정보

- 마지막 동기화 일자: 2026-05-29
- 기준 문서: `../../reference_mdFileList/SDD_V1_0_0528.md`
- 참고: 실제 코드의 도메인 클래스명(`UserAccount`, `LaboratoryProfile` 등)은 기존 명칭을 유지하되, 필드 구성은 SDD 엔티티 명세를 반영한다.

## 개정 이력

| 버전   | 일자       | 변경 내용                                   |
| ------ | ---------- | ------------------------------------------- |
| v1.0.0 | 2026-05-29 | 패키지 구조 및 수정 기준 정리               |
| v1.1.0 | 2026-05-29 | SDD 기준 필드 반영 메모 및 동기화 정보 추가 |
| v1.2.0 | 2026-05-29 | 추적 문서와 공통 메뉴 기능 연결 기준 추가   |

## 1. 개요

`program/src/main/java/com/oose/labsafety` 아래의 코드는 계층형 구조를 따르며, 모듈별로 같은 패턴을 반복한다. 이 문서는 파일을 추가하거나 수정할 때 어디를 먼저 봐야 하는지 안내한다.

## 2. 최상위 구조

```text
program/
  build.gradle
  settings.gradle
  README.md
  docs/
    traceability-map.md
  src/main/java/com/oose/labsafety/
```

## 3. `common`

- `ConsoleIO`: 입력과 출력을 감싼다.
- `Identifiable`: 저장소가 다룰 식별자 규약이다.
- `Displayable`: 목록 출력용 요약 텍스트 규약이다.
- `InMemoryCrudRepository`: 메모리 기반 저장소다.
- `AbstractCatalogService`: 모듈 서비스의 공통 CRUD 흐름이다.
- `FeatureModule`: 메뉴가 따라야 할 공통 계약이다.
- `AbstractCatalogModule`: 표준 메뉴 반복 로직이다.

## 4. `bootstrap`

- `ApplicationBootstrap`: 모듈과 콘솔을 조립한다.
- `ApplicationContext`: 실행 시점 의존성을 묶는다.

## 5. `ui`

- `MainMenu`: 프로그램 시작점 메뉴다.
- 모듈 선택과 종료 흐름을 담당한다.

## 6. 기능 모듈 패키지

각 기능 모듈은 같은 하위 구조를 가진다.

```text
user/
  service/
  domain/
  infrastructure/
  presentation/
```

같은 규칙이 `laboratory`, `chemical`, `waste`, `inspection`, `education`에 반복된다.

## 7. 파일을 수정할 때의 기준

- UI 변경은 `presentation`과 `ui`만 본다.
- 데이터 규칙 변경은 `domain`부터 본다.
- 저장 방식 변경은 `infrastructure`부터 본다.
- 메뉴 동작 변경은 `AbstractCatalogModule`과 각 모듈의 `presentation`을 함께 본다.
- 요구사항-설계-코드 연결 변경은 `docs/traceability-map.md`를 먼저 갱신한다.
