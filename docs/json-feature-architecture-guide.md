# JSON 기반 기능별 계층 구조 구현 가이드

작성일: 2026-06-18

## 1. 목적

이 문서는 연구실 안전관리 React 프로젝트를 여러 팀원이 동일한 구조와 규칙으로 구현하기 위한 작업 기준이다.

프로젝트는 사용자 관리, 연구실 관리, 화학물질 관리, 폐기물 관리, 점검 관리, 안전교육 관리 기능을 각각 독립된 서브시스템으로 구성한다. 데이터베이스 서버는 사용하지 않고, 기본 및 시연 데이터는 JSON 파일로 제공하며 사용자가 화면에서 등록하거나 변경한 데이터는 브라우저 `localStorage`에 JSON 형식으로 저장한다.

## 2. 전체 프로젝트 구조

```text
OoseTeam2/
├─ public/
│  ├─ index.html
│  └─ data/
│     ├─ user/
│     ├─ laboratory/
│     ├─ chemical/
│     ├─ waste/
│     ├─ inspection/
│     └─ education/
│
├─ src/
│  ├─ app/
│  │  ├─ App.jsx
│  │  └─ menuConfig.js
│  │
│  ├─ components/
│  │  ├─ layout/
│  │  ├─ form/
│  │  ├─ table/
│  │  └─ feedback/
│  │
│  ├─ features/
│  │  ├─ user/
│  │  │  ├─ domain/
│  │  │  ├─ service/
│  │  │  ├─ infrastructure/
│  │  │  └─ presentation/
│  │  ├─ laboratory/
│  │  │  ├─ domain/
│  │  │  ├─ service/
│  │  │  ├─ infrastructure/
│  │  │  └─ presentation/
│  │  ├─ chemical/
│  │  │  ├─ domain/
│  │  │  ├─ service/
│  │  │  ├─ infrastructure/
│  │  │  └─ presentation/
│  │  ├─ waste/
│  │  │  ├─ domain/
│  │  │  ├─ service/
│  │  │  ├─ infrastructure/
│  │  │  └─ presentation/
│  │  ├─ inspection/
│  │  │  ├─ domain/
│  │  │  ├─ service/
│  │  │  ├─ infrastructure/
│  │  │  └─ presentation/
│  │  └─ education/
│  │     ├─ domain/
│  │     ├─ service/
│  │     ├─ infrastructure/
│  │     └─ presentation/
│  │
│  ├─ shared/
│  │  ├─ infrastructure/
│  │  ├─ validation/
│  │  ├─ hooks/
│  │  └─ utils/
│  │
│  ├─ styles/
│  └─ index.js
│
├─ docs/
├─ package.json
├─ package-lock.json
├─ .gitignore
└─ README.md
```

## 3. 데이터 저장 원칙

### 3.1 기본 및 시연 데이터

Git으로 공유해야 하는 기본 데이터는 `public/data/<서브시스템>/` 아래의 JSON 파일에 작성한다.

```text
public/data/education/
├─ researcherCategories.json
├─ completionStandards.json
├─ educationCourses.json
├─ educationOpenings.json
└─ educationCompletionResults.json
```

JSON 파일은 초기 데이터이므로 브라우저에서 직접 수정하지 않는다. 데이터 구조가 변경되거나 새로운 시연 데이터가 필요할 때 팀원이 소스 파일을 수정하고 Git에 커밋한다.

### 3.2 사용자가 입력한 데이터

화면에서 등록, 수정, 삭제한 데이터는 `localStorage`에 JSON 문자열로 저장한다.

```text
JSON 초기 데이터 로딩
        ↓
localStorage에 초기화
        ↓
서비스를 통한 등록·조회·수정·삭제
        ↓
localStorage 갱신
```

브라우저는 보안상 프로젝트의 JSON 파일을 직접 덮어쓸 수 없다. 따라서 `public/data`는 초기값, `localStorage`는 실행 중 변경값이라는 역할을 반드시 구분한다.

### 3.3 저장 키 규칙

저장 키는 `oose.<서브시스템>.<엔티티>` 형식을 사용한다.

```text
oose.user.users
oose.laboratory.laboratories
oose.chemical.chemicals
oose.waste.wasteCategories
oose.inspection.inspections
oose.education.researcherCategories
oose.education.completionStandards
oose.education.completionResults
```

키를 임의로 줄이거나 컴포넌트마다 다르게 만들지 않는다.

## 4. 계층별 책임

### 4.1 domain

엔티티의 데이터 구조와 업무 규칙을 정의한다.

- 필수 필드 확인
- ID 형식 확인
- 숫자 범위 확인
- 날짜 순서 확인
- 엔티티 생성 함수 또는 클래스 제공
- React, DOM, `localStorage`, `fetch` 사용 금지

예시:

```javascript
export function createCompletionStandard(input) {
  if (!input.completionStandardId) {
    throw new Error('이수기준 ID는 필수입니다.');
  }
  if (Number(input.requiredHours) <= 0) {
    throw new Error('필수 이수시간은 0보다 커야 합니다.');
  }

  return {
    completionStandardId: input.completionStandardId.trim(),
    researcherCategoryId: input.researcherCategoryId.trim(),
    requiredHours: Number(input.requiredHours),
    effectiveFrom: input.effectiveFrom,
    effectiveTo: input.effectiveTo,
  };
}
```

### 4.2 service

사용자가 수행하는 유스케이스를 구현한다.

- 등록, 조회, 수정, 삭제 흐름 처리
- ID 중복 검사
- 참조 대상 엔티티 존재 여부 검사
- domain을 이용한 데이터 생성 및 검증
- Repository 인터페이스를 통한 데이터 접근
- React 상태와 화면 요소 직접 사용 금지

예를 들어 이수기준 등록 시 `researcherCategoryId`에 해당하는 연구활동종사자 분류가 존재하는지 확인해야 한다.

### 4.3 infrastructure

실제 데이터 입출력을 담당한다.

- 초기 JSON 파일 로딩
- `localStorage` 읽기와 쓰기
- 엔티티별 Repository 구현
- JSON 파싱 오류 처리
- 최초 실행 시에만 기본 데이터 초기화

화면 컴포넌트가 `localStorage.getItem()` 또는 `localStorage.setItem()`을 직접 호출하지 않도록 한다.

### 4.4 presentation

React 화면과 사용자 상호작용을 담당한다.

- 입력 폼 상태 관리
- 등록 및 조회 버튼 처리
- service 호출
- 성공 및 오류 메시지 표시
- 목록, 검색 결과, 빈 상태 렌더링
- 데이터 규칙과 저장 로직을 직접 구현하지 않음

## 5. 서브시스템 내부 표준 구조

모든 담당자는 아래 구조를 동일하게 사용한다.

```text
src/features/<서브시스템>/
├─ domain/
├─ service/
├─ infrastructure/
└─ presentation/
```

예시:

```text
src/features/education/
├─ domain/
├─ service/
├─ infrastructure/
└─ presentation/
```

파일 이름은 다음 규칙을 권장한다.

| 종류 | 규칙 | 예시 |
| --- | --- | --- |
| 엔티티 | 명사, PascalCase | `CompletionStandard.js` |
| 서비스 | 업무명 + `Service` | `educationCompletionService.js` |
| 저장소 | 엔티티명 + `Repository` | `educationRepository.js` |
| 화면 | 기능명 + 동작, PascalCase | `CompletionResultRegister.jsx` |
| JSON | 복수 명사, camelCase | `completionStandards.json` |

## 6. 엔티티 관계 구현 원칙

관련 데이터를 하나의 큰 객체에 모두 합치지 않는다. 엔티티는 각각 분리하고 ID로 참조한다.

```json
{
  "researcherCategoryId": "RC001",
  "categoryName": "학부생",
  "description": "학부 연구 참여자",
  "active": true
}
```

```json
{
  "completionStandardId": "CS001",
  "researcherCategoryId": "RC001",
  "requiredHours": 3,
  "effectiveFrom": "2026-01-01",
  "effectiveTo": "2026-12-31"
}
```

`CompletionStandard`는 분류명 전체를 복사해 저장하지 않고 `researcherCategoryId`만 참조한다. 조회 화면에서 분류명이 필요하면 서비스가 두 엔티티를 조합한 조회 결과를 만든다.

프론트엔드의 JSON 저장은 실제 데이터베이스 외래키를 제공하지 않으므로 서비스 계층에서 다음을 검사한다.

- 참조 ID가 존재하는가
- 같은 ID가 이미 등록되어 있는가
- 참조되는 데이터를 삭제해도 되는가
- 시작일이 종료일보다 늦지 않은가

## 7. 공통 저장소 사용 방식

`src/shared/infrastructure/`에는 특정 서브시스템에 종속되지 않는 JSON 저장 도구를 둔다.

```text
src/shared/infrastructure/
├─ jsonDataLoader.js
└─ localStorageStore.js
```

각 기능의 Repository는 공통 저장 도구를 사용하되, 저장 키와 엔티티별 조회 방식은 해당 기능의 `infrastructure`에서 관리한다.

```javascript
// presentation
const result = await educationCompletionService.register(form);

// service
const existing = repository.findById(form.completionResultId);
repository.save(entity);

// infrastructure
localStorageStore.write(STORAGE_KEY, records);
```

의존 방향은 다음 순서를 지켜야 한다.

```text
presentation → service → domain
                    ↓
              infrastructure
```

`domain`이 `service`, `presentation`, `infrastructure`를 가져오면 안 된다.

## 8. 담당자별 구현 순서

1. 담당 서브시스템의 SRS와 SDD에서 유스케이스와 엔티티를 확인한다.
2. `public/data/<서브시스템>/`에 엔티티별 초기 JSON 파일을 만든다.
3. `domain`에 엔티티 구조와 검증 규칙을 구현한다.
4. `infrastructure`에 JSON 초기화와 `localStorage` Repository를 구현한다.
5. `service`에 등록, 조회, 수정, 삭제 유스케이스를 구현한다.
6. `presentation`에 React 화면을 구현하고 service만 호출한다.
7. `src/app/menuConfig.js`와 `App.jsx`에 화면을 연결한다.
8. 새로고침 후에도 입력 데이터가 유지되는지 확인한다.
9. 브라우저 저장소를 비운 뒤 기본 JSON 데이터가 다시 초기화되는지 확인한다.
10. 빌드와 주요 유스케이스를 검증한 뒤 커밋한다.

## 9. JSON 작성 규칙

- 모든 파일은 UTF-8로 저장한다.
- 속성 이름은 camelCase를 사용한다.
- ID는 문자열로 저장한다.
- 날짜는 `YYYY-MM-DD` 형식을 사용한다.
- 여부 값은 한 방식으로 통일한다. 새 코드에서는 boolean 사용을 권장한다.
- 숫자는 문자열이 아닌 JSON number로 저장한다.
- 마지막 항목 뒤에 쉼표를 넣지 않는다.
- 엔티티별 JSON 최상위 값은 배열로 작성한다.

```json
[
  {
    "completionStandardId": "CS001",
    "researcherCategoryId": "RC001",
    "requiredHours": 3,
    "effectiveFrom": "2026-01-01",
    "effectiveTo": "2026-12-31"
  }
]
```

## 10. React 구현 규칙

- 화면 컴포넌트에서 초기 시연 데이터를 직접 작성하지 않는다.
- 화면 컴포넌트에서 `localStorage`를 직접 접근하지 않는다.
- 등록 전 검증은 domain과 service가 담당한다.
- 화면은 서비스 오류 메시지를 사용자에게 표시한다.
- 목록의 React `key`로 배열 인덱스 대신 엔티티 ID를 사용한다.
- 삭제 전 확인 절차를 제공한다.
- 등록 후 목록을 Repository에서 다시 읽거나 서비스 반환값으로 갱신한다.
- 한 파일에 여러 대형 화면을 모으지 않고 유스케이스별 컴포넌트로 분리한다.

## 11. Git 팀 작업 규칙

각 담당자는 자신의 서브시스템 브랜치에서 작업한다.

```text
feature/user-management
feature/laboratory-management
feature/chemical-management
feature/waste-management
feature/inspection-management
feature/education-management
```

작업 시 다음을 지킨다.

- 다른 담당자의 `features/<서브시스템>` 파일을 임의로 수정하지 않는다.
- 공통 `components`, `shared`, `App.jsx` 변경은 팀원에게 먼저 알린다.
- `node_modules`, `build`, 개인 IDE 설정은 커밋하지 않는다.
- JSON 구조를 변경하면 관련 domain, service, 화면도 함께 수정한다.
- 커밋에는 한 가지 기능 단위의 변경만 포함한다.
- Pull Request에 변경 파일, 실행 방법, 테스트 결과를 기록한다.

## 12. 완료 점검표

담당 기능을 완료하기 전에 다음 항목을 확인한다.

- [ ] `domain`, `service`, `infrastructure`, `presentation`이 분리되어 있다.
- [ ] 기본 데이터가 `public/data`의 JSON 파일에 있다.
- [ ] 사용자가 입력한 데이터가 `localStorage`에 저장된다.
- [ ] 화면에서 `localStorage`를 직접 사용하지 않는다.
- [ ] 엔티티 ID 중복이 검사된다.
- [ ] 하위 엔티티 참조 ID의 존재 여부가 검사된다.
- [ ] 등록, 조회, 수정, 삭제가 정상적으로 동작한다.
- [ ] 검색 결과와 빈 목록 상태가 표시된다.
- [ ] 새로고침 후 입력 데이터가 유지된다.
- [ ] 기본 JSON 초기화가 정상적으로 동작한다.
- [ ] `npm run build`가 성공한다.
- [ ] 담당 기능의 주요 유스케이스를 직접 실행해 확인했다.

## 13. 현재 코드 이전 시 주의사항

현재 `src/components/*Management.jsx`에 화면, 업무 규칙, 저장 로직이 함께 들어 있다면 한 번에 삭제하지 않는다.

각 서브시스템별로 다음 순서로 이전한다.

1. 기존 화면에서 사용하는 데이터 필드를 확인한다.
2. domain과 초기 JSON을 먼저 만든다.
3. Repository와 service를 만든다.
4. 새 presentation 화면을 기존 메뉴에 연결한다.
5. 기존 기능과 결과를 비교한다.
6. 정상 동작을 확인한 뒤 기존 컴포넌트를 제거한다.

이전 기간에는 구 구조와 신 구조가 함께 존재할 수 있지만, 같은 메뉴에서 두 저장 방식을 동시에 사용하면 안 된다.
