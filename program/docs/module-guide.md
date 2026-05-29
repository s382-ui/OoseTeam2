# Module Guide

## 문서 동기화 정보

- 마지막 동기화 일자: 2026-05-29
- 기준 문서: `reference_mdFileList/SDD_V1_0_0528.md`
- 입력 형식 메모: 날짜는 `yyyy-MM-dd`, 일시는 `yyyy-MM-ddTHH:mm` 형식을 사용한다.

## 개정 이력

| 버전   | 일자       | 변경 내용                            |
| ------ | ---------- | ------------------------------------ |
| v1.0.0 | 2026-05-29 | 모듈별 목적/수정 기준 정리           |
| v1.1.0 | 2026-05-29 | SDD 기준 핵심 속성 및 입력 형식 반영 |

## 1. 사용자 모듈

- 목적: 사용자 기본정보 등록/조회 및 계정 상태를 관리한다.
- 중심 파일: `user/domain`, `user/application`, `user/infrastructure`, `user/presentation`
- 핵심 속성: `userId`, `userName`, `department`, `role`, `contact`, `email`, `accountStatus`, `registeredAt`
- 변경 기준: 사용자 식별자 체계, 권한 정책, 계정 상태 정의가 바뀔 때 함께 수정한다.

## 2. 연구실 모듈

- 목적: 연구실 등록/조회 및 위치 중복 확인에 필요한 기본정보를 관리한다.
- 중심 파일: `laboratory/...`
- 핵심 속성: `labId`, `labName`, `buildingName`, `floor`, `roomNo`, `departmentName`, `managerId`, `managerName`, `contactNo`, `managementGrade`, `isActive`, `createdAt`
- 변경 기준: 위치 체계(건물/층/호실), 책임자 식별 방식, 관리등급 체계가 바뀔 때 함께 수정한다.

## 3. 화학물질 모듈

- 목적: 화학물질 마스터 등록/조회 및 MSDS 연계 정보를 관리한다.
- 중심 파일: `chemical/...`
- 핵심 속성: `chemicalId`, `manufacturerName`, `chemicalName`, `casNumber`, `contentRate`, `msdsPath`, `analysisPath`, `status`, `createdAt`
- 변경 기준: 식별 규칙(CAS), 문서 경로 정책(MSDS/분석표), 상태값 정의가 바뀔 때 함께 수정한다.

## 4. 폐기물 모듈

- 목적: 폐기물 분류 등록/조회와 위탁처리 기준을 관리한다.
- 중심 파일: `waste/...`
- 핵심 속성: `categoryCode`, `categoryName`, `categoryType`, `propertyInfo`, `disposalMethod`, `relatedLaw`, `isActive`, `createdAt`
- 변경 기준: 분류체계(지정/일반), 처리방법, 관련 법규 기준이 바뀔 때 함께 수정한다.

## 5. 점검 모듈

- 목적: 점검분야 분류 등록/조회와 사용 여부를 관리한다.
- 중심 파일: `inspection/...`
- 핵심 속성: `categoryCode`, `categoryName`, `categoryDetail`, `useYn`, `createdAt`
- 변경 기준: 분류코드 정책, 점검분야 카테고리, 활성화 기준이 바뀔 때 함께 수정한다.

## 6. 안전교육 모듈

- 목적: 교육이수결과 등록/조회 및 수료증 발급 기반 데이터를 관리한다.
- 중심 파일: `education/...`
- 핵심 속성: `completionResultId`, `researcherId`, `openingId`, `learningResultId`, `logId`, `completionDate`, `recognizedHours`, `completionStatus`, `manualRegistered`
- 변경 기준: 이수 판정 규칙, 인정시간 정책, 온라인/오프라인 이수 연계 정책이 바뀔 때 함께 수정한다.
