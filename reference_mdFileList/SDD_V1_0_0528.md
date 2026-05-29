# 소프트웨어 설계 기술서

> 원본 문서: `소프트웨어 설계 기술서_임시.hwp`
> 변환 기준: 안전교육관리 파트는 추후 작성 대상으로 분리하고, 현재 문서에서는 TODO로 남김.

(Software Design Description)
주제: 연구실 안전관리 시스템 구축

2팀
팀장 : 이성훈
팀원 : 김종규, 서가연, 김소율, 최남규, 서성훈

개정 현황
버전
개정 일자
변경 내용
작성자
승인자
v1.0.0
2026-05-28
SDD 1차 작성
이성훈
PM
목   차
개요	醶ȃ   	4

### 1.1 프로젝트 요약	睠ȃ   	4

### 1.2 목적 및 범위	禈ȃ   	4

### 1.3 운영환경	舜ȃ   	4

### 1.4 업무 분장 내역	甼ȃ   	4
배치 다이어그램	稖ȃ   	7
서브시스템 설계(패키지 다이어그램)	勲ȃ   	8
서브시스템별 설계 모델	梲ȃ   	35

### 4.1 공통 서비스	筴ȃ   	35

### 4.2 사용자 관리	筴ȃ   	36
클래스 다이어그램	款ȃ   	36
Entity 클래스 명세	欦ȃ   	37
테이블 명세	砢ȃ   	64
DB 상세 설계 명세	槺ȃ   	83
Control 클래스 명세	昂ȃ   	nm
Boundary 클래스 명세	懲ȃ   	nm
A. 시퀀스 다이어그램	揊ȃ   	nm

## 1. 개요

### 1.1. 프로젝트 요약

### 1.2. 목적 및 범위
본 문서의 목적은 시스템이 구현하여야 할 설계 내용을 체계적으로 정의함으로써, 개발할 구조와 동작 방식을 명확히 하고, 이를 통해 이해관계자 간의 설계에 대한 공통된 이해를 도모하며, 구현 및 유지보수 과정에서 발생할 수 있는 리스크를 최소화하는 데 목적을 둔다.
본 문서의 범위는 시스템이 제공해야 할 기능을 구현하기 위한 구조적 설계와 모듈 간의 상호작용을 정의하며, 각 기능이 어떤 방식으로 시스템 내부 구성요소와 연동되는지를 기술하고, 이 과정에서 사용되는 데이터의 흐름과 저장 방식을 설계 관점에서 분석한다.

### 1.3. 운영환경

구분
개발자 환경
시스템 운영 환경
운영체제
Windows 11
Windows 11
개발 언어
Java 17
Java 17
데이터베이스
MySQL 8.0
MySQL 8.0
IDE 및 개발 도구
VS Code

### 1.4. 업무 분장 내역

서브시스템
유스케이스
담당자
식별자
유스케이스 명

## 2. 배치 다이어그램 (Deployment Diagram)

배치 다이어그램

## 3. 서브시스템 설계 (Design Subsystem)

패키지 다이어그램

## 4. 서브시스템별 설계 모델

### 4.1 공통 서비스

설계 클래스 다이어그램 ID
클래스 구분
Boundary
클래스명
메인 화면 / Main UI
설명
시스템 사용자가 연구실 안전관리 시스템의 각 서브시스템 연구에 접근하고, 등록 / 조회 기능 화면으로 이동할 수 있도록 메뉴를 제공하는 공통 사용자 인터페이스 클래스
지속성
Transient

오퍼레이션
가시성
설명
MainUI()
public
메인 화면을 생성하고 시스템 메뉴를 초기화한다.
showUserManagementMenu()
public
사용자 관리 메뉴 화면을 출력한다.
showLaboratoryManagementMenu()
public
연구실 관리 메뉴 화면을 출력한다.
showChemicalManagementMenu()
public
화학물질 관리 메뉴 화면을 출력한다.
showWasteManagementMenu()
public
폐기물 관리 메뉴 화면을 출력한다.
showSafetyEducationMenu()
public
안전교육 관리 메뉴 화면을 출력한다.
showChecklistManagementMenu()
public
일상점검 관리 메뉴 화면을 출력한다.
showStatisticsMenu()
public
통계 및 현황 메뉴 화면을 출력한다.
logout()
public
현재 사용자 세션을 종료하고 로그인 화면으로 이동한다.

### 4.2 사용자 관리
- 클래스 다이어그램

서브시스템 ID
DSS-001
서브시스템 명
사용자 관리

- Entity 클래스

설계 클래스
다이어그램 ID
DCD-001
설계 서브시스템 ID
DSS-001
클래스 구분
Entity
Entity ID
ET-001
클래스명
사용자 / User
설명
시스템 사용자의 기본 정보, 소속, 권한 및 상태를 담은 클래스
지속성
Persistence

변수명
가시성
자료형
설명
userId
private
String
사용자 고유 식별 번호 (학번/사번)
userName
private
String
사용자 이름
department
private
String
사용자가 속한 학과 또는 연구실
role
private
String
시스템 내 사용자 권한
contact
private
String
휴대전화 번호
email
private
String
사용자 이메일 주소
accountStatus
private
String
계정의 현재 유효 상태
registeredAt
private
DateTime
사용자 계정 등록(DB 최종 저장) 일시
- 테이블 명세

Entity Id
ET-001
Entity 명
User
필드명
동의어
자료 형식
자료 크기
NOT NULL
PK
FK
userId
사용자ID
VARCHAR
20
Y
Y
userName
성명
VARCHAR
50
Y
department
소속
VARCHAR
100
Y
role
역할
VARCHAR
20
Y
contact
연락처
VARCHAR
20
Y
email
이메일
VARCHAR
100
N
accountStatus
계정상태
VARCHAR
20
Y
registeredAt
등록일시
DATETIME
8
Y
- DB 상세 설계 명세

관련 Entity
ET-001
테이블 명
User
1) 사용자 정보 단건/대량 등록
INSERT INTO User (
userId,
userName,
department,
role,
contact,
email,
accountStatus,
registeredAt
) VALUES (
?,
?,
?,
?,
?,
?,
?,
?
);
2) 사용자 정보 조회
SELECT
userId, userName, department, role, contact, email, registeredAt
FROM User
WHERE userId = ?;
- Control 클래스

설계 클래스
다이어그램 ID
DCD-002
설계 서브시스템 ID
DSS-001
클래스 구분
Control
클래스명
사용자 관리 서비스 / UserManagementService
설명
사용자 등록, 조회, 유효성 검증 등 사용자 관리 비즈니스 로직을 담당하는 클래스
지속성
Transient

오퍼레이션명
가시성
파라미터 / 반환값
설명
validateUserData()
public
User, File / Boolean
입력된 데이터(단건) 또는 업로드된 파일(대량)의 유효성 및 필수 항목 누락을 검증한다.
searchSchoolData()
public
String(userId) / User
학내 데이터베이스를 조회하여 해당 사용자의 기본 정보를 반환한다.
registerUser()
public
User / Boolean
학내 데이터 조회 후 확인된 사용자 단건을 시스템에 등록 처리한다.
registerUsersBulk()
public
List<User> / Boolean
검증이 완료된 다수의 사용자 데이터를 일괄 등록 처리한다.
getUserInfo()
public
String(userId) / User
특정 사용자의 상세 정보를 조회하여 반환한다.

설계 클래스
다이어그램 ID
DCD-003
설계 서브시스템 ID
DSS-001
클래스 구분
Control
클래스명
사용자 저장소 / UserRepository
설명
사용자 데이터의 DB 접근(저장, 조회)을 담당하는 인터페이스 클래스
지속성
Transient

오퍼레이션명
가시성
파라미터 / 반환값
설명
save()
public
User / Boolean
단일 사용자 정보를 DB에 저장한다.
saveAll()
public
List<User> / Boolean
다수의 사용자 정보를 DB에 일괄 저장한다.
findById()
public
String(userId) / User
식별자(학번/사번)를 기준으로 DB에서 사용자 정보를 검색한다.
checkDuplicate()
public
String(userId) / Boolean
시스템에 이미 등록된 사용자인지 중복 여부를 검사한다.

설계 클래스
다이어그램 ID
DCD-004
설계 서브시스템 ID
DSS-001
클래스 구분
Control
클래스명
학내 데이터베이스 연동 어댑터 / SchoolDatabaseAdapter
설명
연동된 학내 외부 데이터베이스 시스템에 접근하여 학번 또는 사번을 기반으로 사용자의 기본 인적 사항을 조회하고 시스템 내부 엔티티 형식으로 매핑하는 클래스
지속성
Transient

오퍼레이션명
가시성
파라미터 / 반환값
설명
fetchStudentOrStaffInfo()
public
String(userId) / User
외부 학내 데이터베이스 시스템을 쿼리하여 입력받은 학번/사번과 일치하는 사용자 정보를 검색한 후, 이를 User 엔티티 객체로 변환하여 반환한다. 일치하는 데이터가 없을 경우 null을 반환한다.
- Boundary 클래스(사용자 정보 등록)

설계 클래스
다이어그램 ID
DCD-005
설계 서브시스템 ID
DSS-001
클래스 구분
Boundary
클래스명
사용자 정보 등록 View / UserRegistrationView
설명
학내 사용자를 조회하고 신규 등록을 요청하는 UI 클래스
지속성
Transient

오퍼레이션
가시성
설명
UserRegistrationView()
public
사용자 등록 화면을 생성하고 UI 요소를 초기화한다.
searchUser()
public
학번/사번을 입력받아 학내 데이터베이스 조회를 요청하고 결과를 출력한다.
onSubmit()
public
출력된 정보를 확인 후 최종적으로 시스템 DB 등록을 요청한다.
- Boundary 클래스(사용자 대량 등록)

설계 클래스
다이어그램 ID
DCD-006
설계 서브시스템 ID
DSS-001
클래스 구분
Boundary
클래스명
사용자 대량 등록 View / UserBulkRegistrationView
설명
사용자 정보 양식 파일을 업로드하여 일괄 등록을 처리하는 UI 클래스
지속성
Transient

오퍼레이션
가시성
설명
UserBulkRegistrationView()
public
사용자 대량 등록 화면을 생성하고 UI 요소를 초기화한다.
uploadFile()
public
사용자 정보가 작성된 양식 파일을 시스템에 첨부한다.
onSubmit()
public
첨부된 파일의 검증을 거쳐 최종적으로 일괄
저장을 요청한다.
- Boundary 클래스(사용자 정보 조회)

설계 클래스
다이어그램 ID
DCD-007
설계 서브시스템 ID
DSS-001
클래스 구분
Boundary
클래스명
사용자 정보 조회 View / UserInfoView
설명
권한에 따라 특정 사용자 또는 본인의 상세 정보를 출력하는 UI 클래스
지속성
Transient

오퍼레이션
가시성
설명
UserInfoView()
public
사용자 상세 정보 조회 화면을 생성하고 초기화한다.
showUserInfo()
public
검색된 사용자(User) 엔티티 데이터를 받아와 화면에 텍스트로 렌더링한다.
- 시퀀스 다이어그램(사용자 정보 등록)

설계 시퀀스
다이어그램 ID
설계 서브시스템 ID
DSS-001
유스케이스 명
사용자 정보 등록
서브시스템 명
사용자 관리

- 시퀀스 다이어그램(사용자 대량 등록)

설계 시퀀스
다이어그램 ID
설계 서브시스템 ID
DSS-001
유스케이스 명
사용자 대량 등록
서브시스템 명
사용자 관리

- 시퀀스 다이어그램(사용자 정보 조회)

설계 시퀀스
다이어그램 ID
설계 서브시스템 ID
DSS-001
유스케이스 명
사용자 정보 조회
서브시스템 명
사용자 관리

### 4.3 연구실 관리
- 클래스 다이어그램

서브시스템 ID
DSS-002
서브시스템 명

- Entity 클래스

설계 클래스
다이어그램 ID
설계 서브시스템 ID
DSS-002
클래스 구분
Entity
Entity ID
클래스명
연구실 관리 / Laboratory
설명
연구실의 명칭, 위치, 소속, 책임자, 연락처, 관리등급, 활성 상태 등 연구실 관리에 필요한 기본정보를 담는 클래스
지속성
Persistence

변수명
가시성
자료형
설명
labId
private
String
연구실 고유 식별 ID
labName
private
String
연구실 명칭
buildingName
private
String
건물명
floor
private
String
층 정보
roomNo
private
String
호실 정보
departmentName
private
String
소속 학과 또는 부서명
managerId
private
String
연구실 책임자 사용자 ID
managerName
private
String
연구실 책임자명
contactNo
private
String
대표 연락처
managementGrade
private
String
안전관리 등급
isActive
private
String
활성 상태 여부
createdAt
private
DateTime
등록 일시
- 테이블 명세

Entity Id
Laboratory
Entity 명
연구실 관리
필드명
동의어
자료 형식
자료 크기
NOT NULL
PK
FK
labId
연구실ID
VARCHAR
20
Y
Y
labName
연구실명
VARCHAR
100
Y
buildingName
건물명
VARCHAR
100
Y
floor
층
VARCHAR
10
Y
roomNo
호실
VARCHAR
20
Y
departmentName
소속학과
VARCHAR
100
Y
managerId
책임자ID
VARCHAR
20
Y
Y
managerName
책임자명
VARCHAR
50
Y
contactNo
연락처
VARCHAR
20
managementGrade
관리등급
VARCHAR
20
isActive
사용여부
CHAR
1
Y
createdAt
등록일시
DATETIME
8
Y
- DB 상세 설계 명세

관련 Entity
Laboratory
테이블 명
Laboratory
-- 연구실 등록
INSERT INTO Laboratory (labId, labName, buildingName, floor, roomNo, departmentName, managerId, managerName, contactNo, managementGrade, isActive, createdAt)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Y', NOW());

-- 연구실 위치 중복 여부 확인
SELECT COUNT(*) AS duplicateCount
FROM Laboratory
WHERE buildingName = ? AND floor = ? AND roomNo = ? AND isActive = 'Y';
-- 연구실 목록 조회
SELECT labId, labName, buildingName, floor, roomNo,
departmentName, managerName, managementGrade, isActive
FROM Laboratory
WHERE isActive = 'Y'
AND (? IS NULL OR labName LIKE CONCAT('%', ?, '%'))
AND (? IS NULL OR buildingName LIKE CONCAT('%', ?, '%'))
AND (? IS NULL OR roomNo LIKE CONCAT('%', ?, '%'))
AND (? IS NULL OR departmentName LIKE CONCAT('%', ?, '%'))
AND (? IS NULL OR managerName LIKE CONCAT('%', ?, '%'))
ORDER BY createdAt DESC;

-- 연구실 상세 조회
SELECT *
FROM Laboratory
WHERE labId = ?
AND isActive = 'Y';
- Control 클래스

설계 클래스
다이어그램 ID
설계 서브시스템 ID
DSS-002
클래스 구분
Control
클래스명
연구실 관리 컨트롤 / LaboratoryManagementControl
설명
연구실 정보의 등록, 조회, 상세 조회, 입력값 검증, 중복 여부 확인, 저장 및 검색 처리를 담당하는 제어 클래스
지속성
Transient

오퍼레이션명
가시성
파라미터 / 반환값
설명
registerLaboratory()
public
Laboratory / Boolean
입력된 연구실 정보를 검증한 뒤 신규 연구실 정보를 등록한다.
searchLaboratory()
public
LaboratorySearchCondition / List<Laboratory>
연구실명, 건물명, 학과, 책임자 등 조회 조건에 맞는 연구실 목록을 조회한다.
getLaboratoryDetail()
public
String / Laboratory
연구실 ID를 기준으로 특정 연구실의 상세 정보를 조회한다.
validateRequiredFields()
private
Laboratory / Boolean
연구실명, 건물명, 층, 호실, 학과, 책임자 등 필수 입력값의 누락 여부를 검증한다.
validateFormat()
private
Laboratory / Boolean
연락처, 층, 호실, 관리등급 등 입력값 형식이 기준에 맞는지 검증한다.
checkDuplicateLaboratory()
private
Laboratory / Boolean
건물명, 층, 호실 기준으로 동일 연구실이 이미 등록되어 있는지 확인한다.
saveLaboratory()
private
Laboratory / Boolean
검증이 완료된 연구실 정보를 DB에 저장한다.
validateSearchCondition()
private
LaboratorySearchCondition / Boolean
조회 조건의 형식과 입력 가능 여부를 검증한다.
findLaboratoryList()
private
LaboratorySearchCondition / List<Laboratory>
조회 조건에 맞는 연구실 목록을 DB에서 검색한다.
findLaboratoryById()
private
String / Laboratory
연구실 ID에 해당하는 단일 연구실 정보를 DB에서 조회한다.
- Boundary 클래스(연구실 등록)

설계 클래스
다이어그램 ID
설계 서브시스템 ID
DSS-002
클래스 구분
Control
클래스명
연구실 관리 컨트롤 / LaboratoryManagementControl
설명
연구실 정보의 등록, 조회, 상세 조회, 입력값 검증, 중복 여부 확인, 저장 및 검색 처리를 담당하는 제어 클래스
지속성
Transient

오퍼레이션명
가시성
파라미터 / 반환값
설명
registerLaboratory()
public
Laboratory / Boolean
입력된 연구실 정보를 검증한 뒤 신규 연구실 정보를 등록한다.
searchLaboratory()
public
LaboratorySearchCondition / List<Laboratory>
연구실명, 건물명, 학과, 책임자 등 조회 조건에 맞는 연구실 목록을 조회한다.
getLaboratoryDetail()
public
String / Laboratory
연구실 ID를 기준으로 특정 연구실의 상세 정보를 조회한다.
validateRequiredFields()
private
Laboratory / Boolean
연구실명, 건물명, 층, 호실, 학과, 책임자 등 필수 입력값의 누락 여부를 검증한다.
validateFormat()
private
Laboratory / Boolean
연락처, 층, 호실, 관리등급 등 입력값 형식이 기준에 맞는지 검증한다.
checkDuplicateLaboratory()
private
Laboratory / Boolean
건물명, 층, 호실 기준으로 동일 연구실이 이미 등록되어 있는지 확인한다.
saveLaboratory()
private
Laboratory / Boolean
검증이 완료된 연구실 정보를 DB에 저장한다.
validateSearchCondition()
private
LaboratorySearchCondition / Boolean
조회 조건의 형식과 입력 가능 여부를 검증한다.
findLaboratoryList()
private
LaboratorySearchCondition / List<Laboratory>
조회 조건에 맞는 연구실 목록을 DB에서 검색한다.
findLaboratoryById()
private
String / Laboratory
연구실 ID에 해당하는 단일 연구실 정보를 DB에서 조회한다.
- Boundary 클래스(연구실 조회)

설계 클래스
다이어그램 ID
설계 서브시스템 ID
DSS-002
클래스 구분
Boundary
클래스명
연구실 조회 / LaboratorySearchView
설명
시스템 관리자가 연구실 조회 조건을 입력하고 결과 목록 및 상세 정보를 확인하는 UI 클래스
지속성
Transient

오퍼레이션
가시성
설명
LaboratorySearchView()
public
연구실 기본정보 조회 화면을 생성하고 UI 초기화 및 이벤트를 설정한다.
initForm()
private
조회 조건 입력 항목과 목록 출력 영역을 초기화한다.
updateList()
public
전체 연구실 목록을 조회하여 화면에 출력한다.
updateList(condition: LaboratorySearchCondition)
public
조회 조건을 기준으로 연구실 목록을 조회한다.
fillTable(labList: List)
private
전달받은 연구실 목록 데이터를 테이블 또는 그리드에 출력한다.
showDetail(lab: Laboratory)
public
선택한 연구실의 상세 정보를 화면에 출력한다.
showMessage(message: String)
private
조회 결과 없음 또는 오류 메시지를 출력한다.
- 시퀀스 다이어그램(연구실 관리 등록 / 조회)

설계 시퀀스
다이어그램 ID
설계 서브시스템 ID
DSS-002
유스케이스 명
연구실 관리 (등록/조회)
서브시스템 명
연구실 관리

### 4.4 화학물질 관리
- 클래스 다이어그램

서브시스템 ID
서브시스템 명
- Entity 클래스

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Entity
Entity ID
클래스명
화학물질 마스터 / ChemicalMaster
설명
화학물질의 기본 마스터 정보를 담은 클래스
지속성
Persistence

변수명
가시성
자료형
설명
chemicalId
private
String
화학물질 고유 식별 번호
manufacturerName
private
String
제조사 이름
chemicalName
private
String
화학물질 명칭
casNumber
private
String
CAS 등록 번호
contentRate
private
String
성분 함유량 정보
msdsPath
private
String
MSDS 파일 경로
analysisPath
private
String
성분분석표 파일 경로
status
private
String
활성 상태(정상/삭제)
createdAt
private
DateTime
최초 등록 일시
- 테이블 명세

Entity Id
Entity 명
화학물질 마스터 / ChemicalMaster
필드명
동의어
자료 형식
자료 크기
NOT NULL
PK
FK
chemicalId
화학물질 Id
VARCHAR
20
Y
Y
manufacturerName
제조사명
VARCHAR
100
Y
chemicalName
화학물질명
VARCHAR
200
Y
casNumber
CAS 번호
VARCHAR
50
contentRate
함유량
VARCHAR
100
msdsPath
MSDS 경로
VARCHAR
255
analysisPath
분석표경로
VARCHAR
255
status
상태
VARCHAR
20
Y
createdAt
등록일시
DATETIME
8
Y
- DB 상세 설계 명세

관련 Entity
테이블 명
ChemicalMaster, ReagentStock, NewProductInfo, StatisticsData
1) 화학물질 등록
INSERT INTO ChemicalMaster (
chemicalId, manufacturerName, chemicalName, casNumber,
contentRate, msdsPath, analysisPath, status, createdAt
) VALUES (?, ?, ?, ?, ?, ?, ?, 'ACTIVE', NOW());
2) 화학물질 조회 (제조사 / 물질명 조건)
-- 목록 조회
SELECT * FROM ChemicalMaster
WHERE status = 'ACTIVE'
AND (manufacturerName LIKE ? OR chemicalName LIKE ?)
ORDER BY createdAt DESC;
-- 상세 조회
SELECT * FROM ChemicalMaster WHERE chemicalId = ?;
- Control 클래스

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Control
클래스명
화학물질 관리 서비스 / ChemicalManagementService
설명
입력값 검증, 중복 확인, 외부 API 연동, 통계 집계 등 화학물질 관리 비즈니스 로직을 담당하는 클래스
지속성
Transient

오퍼레이션명
가시성
파라미터 / 반환값
설명
validateInput()
public
ChemicalMaster / Boolean
화학물질 등록 시 필수항목 누락 및 형식 유효성을 검사한다.
checkDuplicate()
public
String / Boolean
CAS 번호 기준으로 이미 등록된 화학물질인지 확인한다.
requestExternalApi()
public
String / MsdsInfo
산업안전보건공단 API를 통해 실시간 MSDS 상세 정보를 조회한다.

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Control
클래스명
화학물질 정보 저장소 / ChemicalRepository
설명
화학물질 마스터 및 시약 재고 데이터의 DB 저장 및 조회를 담당하는 클래스
지속성
Transient

오퍼레이션명
가시성
파라미터 / 반환값
설명
save()
public
String / Boolean
화학물질 마스터 정보를 DB에 저장한다.
findByCondition()
public
String / List
조건에 맞는 화학물질 정보를 DB에서 조회한다.
findDetail()
public
String / ChemicalMaster
특정 화학물질의 상세 정보를 조회한다.
saveStock()
public
ReagentStock / Boolean
시약 재고정보를 DB에 저장한다.
findStock()
public
String / List
조건에 맞는 시약 재고 리스트를 조회한다.
saveProduct()
public
NewProductInfo / Boolean
신규 제품 정보를 DB에 저장한다.
findProduct()
public
String / List
조건에 맞는 신규제품 정보를 조회한다.
- Boundary 클래스(화학물질 등록)

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Boundary
클래스명
화학물질 등록 View / ChemicalRegisterView
설명
화학물질 등록 정보를 입력하고 ChemicalManagementService와 상호작용하는 UI 클래스
지속성
Transient

오퍼레이션
가시성
설명
ChemicalRegisterView()
public
등록 화면을 생성하고 UI 초기화 및 이벤트를 설정한다.
initForm()
private
UI 폼 요소를 초기화 하고 레이아웃을 구성한다.
loadManufacturers()
private
제조사 목록을 불러와 선택 목록에 세팅한다.
onFileUpload()
public
MSDS PDF 및 성분분석표 파일을 업로드 처리한다.
onSubmit()
public
사용자가 입력한 화학물질 정보를 등록 요청 처리한다.
clearForm()
private
폼의 모든 입력 항목을 초기 상태로 리셋한다.
- Boundary 클래스(화학물질 조회)

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Boundary
클래스명
화학물질 조회 View / ChemicalInquiryView
설명
화학물질 현황을 조회하고 결과를 출력하는 UI 클래스
지속성
Transient

오퍼레이션
가시성
설명
ChemicalInquiryView()
public
조회 화면을 생성하고 UI 초기화 및 이벤트를 설정한다.
updateList(condition : String)
public
조건이 null 또는 빈 값이면 전체 목록을, 값이 있으면 제조사 또는 물질명 조건으로 검색하여 테이블에 출력한다.
showDetail(chemicalId : String)
public
선택된 화학물질의 상세 정보 화면(MSDS 등)을 출력한다.
requestMsds(chemicalId : String)
public
외부 API를 통해 실시간 MSDS 상세 정보를 웹페이지 형태로 출력한다.
fillTable(chemicals : List)
private
전달받은 화학물질 리스트를 테이블에 출력한다.
- 시퀀스 다이어그램(화학물질 등록)

설계 시퀀스
다이어그램 ID
설계 서브시스템 ID
유스케이스 명
화학물질 등록
서브시스템 명
화학물질 관리

- 시퀀스 다이어그램(화학물질 조회)

설계 시퀀스
다이어그램 ID
설계 서브시스템 ID
유스케이스 명
화학물질 조회
서브시스템 명
화학물질 관리

### 4.5 폐기물 관리
- 클래스 다이어그램

서브시스템 ID
서브시스템 명

- Entity 클래스

설계 클래스
다이어그램 ID
DCD-
설계 서브시스템 ID
DSS-
클래스 구분
Entity
Entity ID
ET-
클래스명
폐기물 분류 / WasteCategory
설명
폐기물 종류 및 성상 분류 정보를 담은 클래스
지속성
Persistence

변수명
가시성
자료형
설명
categoryCode
private
String
폐기물 분류 고유 코드
categoryName
private
String
폐기물 분류 이름
categoryType
private
String
지정 / 일반 구분
propertyInfo
private
String
폐기물 물리적
상태 정보
disposalMethod
private
String
폐기물 물리적 상태 정보
relatedLaw
private
String
적용 법규 정보
isActive
private
String
활성 상태 여부
createdAt
private
DateTime
최초 등록 일시
- 테이블 명세

Entity Id
ET-
Entity 명
WasteCategory
필드명
동의어
자료 형식
자료 크기
NOT NULL
PK
FK
categoryCode
분류코드
VARCHAR
20
Y
Y
categoryName
분류명
VARCHAR
100
Y
categoryType
분류유형
VARCHAR
20
Y
propertyInfo
성상정보
VARCHAR
100
disposalMethod
위탁처리방법
VARCHAR
100
relatedLaw
관련법규
VARCHAR
50
isActive
사용여부
VARCHAR
10
Y
createdAt
등록일시
DATETIME
8
Y
- DB 상세 설계 명세

관련 Entity
ET-
테이블 명
WasteCategory
1) 폐기물 분류 등록
INSERT INTO WasteCategory (
categoryCode,
categoryName,
categoryType,
propertyInfo,
disposalMethod,
relatedLaw,
isActive,
createdAt
) VALUES (
?,
?,
?,
?,
?,
?,
?,
?
);
2) 폐기물 분류 조회
-- 전체 분류 조회
SELECT *
FROM WasteCategory
WHERE isActive = 'Y'
ORDER BY createdAt DESC;
-- 분류 유형별 조회
SELECT *
FROM WasteCategory
WHERE isActive = 'Y'
AND categoryType = ?
ORDER BY createdAt DESC;
-- 분류 상세 조회
SELECT *
FROM WasteCategory
WHERE categoryCode = ?;
- Control 클래스

설계 클래스
다이어그램 ID
DCD-
설계 서브시스템 ID
DSS-
클래스 구분
Control
클래스명
폐기물 관리 서비스 / WasteManagementService
설명
전표 유효성 검사, 성상 매칭, 통계 계산 등 폐기물 관리 비즈니스 로직을 담당하는 클래스
지속성
Transient

오퍼레이션명
가시성
파라미터 / 반환값
설명
validateCategory()
public
WasteCategory / Boolean
분류 유효성 검사 등 폐기물 분류 관리 비즈니스 로직을 담당하는 클래스

설계 클래스
다이어그램 ID
DCD-
설계 서브시스템 ID
DSS-
클래스 구분
Control
클래스명
폐기물 정보 저장소 / WasteRepository
설명
폐기물 전표 및 현황 데이터의 DB 저장 및 조회를 담당하는 클래스
지속성
Transient

오퍼레이션명
가시성
파라미터 / 반환값
설명
save()
public
WasteCategory / Boolean
분류 정보를 DB에 저장한다.
findByCondition()
public
String / List
조건에 맞는 폐기물 정보를 DB에서 조회한다.
update()
public
WasteCategory / Boolean
기존 분류 정보를 수정한다.
softDelete()
public
String / Boolean
해당 데이터의 상태를 삭제로 변경한다.
- Boundary 클래스(폐기물 분류 등록)

설계 클래스
다이어그램 ID
DCD-
설계 서브시스템 ID
DSS-
클래스 구분
Boundary
클래스명
폐기물 분류 등록 View / WasteCategoryRegisterView
설명
폐기물 분류 등록 정보를 입력하고 WasteManagementService와 상호 작용하는 UI 클래스
지속성
Transient

오퍼레이션
가시성
설명
WasteCategoryRegisterView()
public
분류 등록 화면을 생성하고 UI 초기화 및 이벤트를 설정한다.
initForm()
private
UI 폼 요소를 초기화 하고 레이아웃을 구성한다.
onSubmit()
public
사용자가 입력한 분류 정보를 등록 요청 처리한다.
clearForm()
private
폼의 모든 입력 항목을 초기 상태로 리셋한다.
- Boundary 클래스(폐기물 분류 조회)

설계 클래스
다이어그램 ID
DCD-
설계 서브시스템 ID
DSS-
클래스 구분
Boundary
클래스명
폐기물 분류 조회 View / WasteCategoryInquiryView
설명
폐기물 분류 현황을 조회하고 결과를 출력하는 UI 크래스
지속성
Transient

오퍼레이션
가시성
설명
WasteCategoryInquiryView()
public
분류 조회 화면을 생성하고 UI 초기화 및 이벤트를 설정한다.
updateList()
public
전체 분류 목록을 DB에서 조회하여 테이블에 출력한다.
UpdateList(categoryType : String)
public
특정 분류 유형을 기준으로 검색하여 테이블에 출력한다.
fillTable(categories : List<WasteCategory>)
private
전달받은 분류 리스트를 테이블에 출력한다.
- 시퀀스 다이어그램(폐기물 분류 등록)

설계 시퀀스
다이어그램 ID
DSD-
설계 서브시스템 ID
DSS-
유스케이스 명
폐기물 분류 등록
서브시스템 명
폐기물 관리

- 시퀀스 다이어그램(폐기물 분류 조회)

설계 시퀀스
다이어그램 ID
DSD-
설계 서브시스템 ID
DSS-
유스케이스 명
폐기물 분류 조회
서브시스템 명
폐기물 관리

### 4.6 일상점검 관리
- 클래스 다이어그램

서브시스템 ID
서브시스템 명

- Entity 클래스

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Entity
Entity ID
ChecklistItem
클래스명
체크리스트 항목 / ChecklistItem
설명
일상점검에 사용할 개별 점검 항목명, 배점, 점검유형 정보를 담은 클래스
지속성
Persistence

변수명
가시성
자료형
설명
itemId
private
String
체크리스트 항목 고유 식별 번호
itemName
private
String
점검 항목명
categoryName
private
String
점검 항목이 속한 분야명
score
private
int
항목별 배점
inspectionType
private
String
점검유형
requiredYn
private
boolean
필수 입력 여부
useYn
private
boolean
항목 사용 여부
createdAt
private
DateTime
항목 등록 일시
- 테이블 명세

Entity Id
Entity 명
필드명
동의어
자료 형식
자료 크기
NOT NULL
PK
FK
itemId
항목번호
VARCHAR
20
Y
Y
itemName
항목명
VARCHAR
200
Y
categoryName
분야명
VARCHAR
100
Y
score
배점
INT
4
Y
inspectionType
점검유형
VARCHAR
30
Y
requiredYn
필수여부
BOOLEAN
1
Y
useYn
사용여부
BOOLEAN
1
Y
createdAt
등록일시
DATETIME
8
Y
- DB 상세 설계 명세

관련 Entity
ChecklistItem
테이블 명
1) 체크리스트 항목 등록
INSERT INTO ChecklistItem (
itemId,
itemName,
categoryName,
score,
inspectionType,
requiredYn,
useYn,
createdAt
) VALUES (
?, ?, ?, ?, ?, ?, TRUE, NOW()
);
2) 체크리스트 항목명 중복 확인
SELECT
itemId,
itemName
FROM ChecklistItem
WHERE itemName = ?
AND categoryName = ?
AND inspectionType = ?
AND useYn = TRUE;
3) 체크리스트 항목 전체 조회
SELECT
itemId,
itemName,
categoryName,
score,
inspectionType,
requiredYn,
useYn,
createdAt
FROM ChecklistItem
WHERE useYn = TRUE
ORDER BY categoryName, itemName;
4) 체크리스트 항목 조건 조회
SELECT
itemId,
itemName,
categoryName,
score,
inspectionType,
requiredYn,
useYn,
createdAt
FROM ChecklistItem
WHERE useYn = TRUE
AND itemName LIKE CONCAT('%', ?, '%')
AND categoryName LIKE CONCAT('%', ?, '%')
AND inspectionType LIKE CONCAT('%', ?, '%')
ORDER BY categoryName, itemName;
5) 필터 초기화 후 전체 항목 조회
SELECT
itemId,
itemName,
categoryName,
score,
inspectionType,
requiredYn,
useYn,
createdAt
FROM ChecklistItem
WHERE useYn = TRUE
ORDER BY categoryName, itemName;
- Control 클래스

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Control
클래스명
체크리스트 빌더 서비스 / ChecklistBuilderService
설명
체크리스트 항목 등록, 필수 입력값 검증, 기존 항목 복사, 항목 조건 조회 등 체크리스트 빌더 등록/조회 비즈니스 로직을 담당하는 클래스
지속성
Transient

오퍼레이션명
가시성
파라미터 / 반환값
설명
validateItem()
public
ChecklistItem / Boolean
항목명, 배점, 점검유형 등 필수 입력값 누락 여부를 검사한다.
checkDuplicateItem()
public
ChecklistItem / Boolean
동일한 항목명, 분야, 점검유형의 항목 존재 여부를 확인한다.
registerItem()
public
ChecklistItem / Boolean
신규 체크리스트 항목을 등록한다.
copyItem()
public
String / ChecklistItem
기존 항목 정보를 불러와 복사용 입력 데이터로 반환한다.
getItemList()
public
ChecklistSearchCondition / List
검색 조건에 맞는 체크리스트 항목 목록을 조회한다.
\resetFilter()
public
- / List
필터 조건을 초기화하고 전체 항목 목록을 반환한다.

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Control
클래스명
체크리스트 항목 저장소 / ChecklistItemRepository
설명
체크리스트 항목 데이터의 DB 저장, 중복 확인, 조건별 조회를 담당하는 클래스
지속성
Transient

오퍼레이션명
가시성
파라미터 / 반환값
설명
save()
public
ChecklistItem / Boolean
체크리스트 항목 정보를 DB에 저장한다.
existsByItemInfo()
public
ChecklistItem / Boolean
동일 조건의 항목이 존재하는지 확인한다.
findById()
public
String / ChecklistItem
특정 체크리스트 항목 상세 정보를 조회한다.
findByCondition()
public
ChecklistSearchCondition / List
검색 조건에 맞는 체크리스트 항목 목록을 조회한다.
findAll()
public
- / List
전체 체크리스트 항목 목록을 조회한다.
- Boundary 클래스(체크리스트 항목 등록)

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Boundary
클래스명
체크리스트 항목 등록 View / ChecklistItemRegisterView
설명
시스템 관리자가 항목명, 배점, 점검유형 등을 입력하여 일상점검 체크리스트 항목을 등록하는 UI 클래스
지속성
Transient

오퍼레이션
가시성
설명
ChecklistItemRegisterView()
public
체크리스트 항목 등록 화면을 생성하고 UI 초기화 및 이벤트를 설정한다.
initForm()
private
항목명, 배점, 점검유형 입력 폼을 초기화한다.
copyItem()
public
기존 항목 내용을 불러와 입력 창에 표시한다.
onRegister()
public
사용자가 입력한 체크리스트 항목 등록 요청을 처리한다.
showMessage()
private
등록 완료 또는 오류 메시지를 화면에 출력한다.
clearForm()
private
입력 폼을 초기 상태로 리셋한다.
- Boundary 클래스(체크리스트 항목 조회)

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Boundary
클래스명
체크리스트 항목 조회 View / ChecklistItemSearchView
설명
시스템 관리자가 검색 조건을 입력하여 등록된 체크리스트 항목 목록을 조회하는 UI 클래스
지속성
Transient

오퍼레이션
가시성
설명
ChecklistItemSearchView()
public
체크리스트 항목 조회 화면을 생성하고 UI 초기화 및 이벤트를 설정한다.
initForm()
private
검색 조건 입력 영역과 항목 리스트 영역을 초기화한다.
updateList()
public
전체 체크리스트 항목 목록을 조회하여 화면에 출력한다.
updateList(ChecklistSearchCondition condition)
public
조건에 맞는 체크리스트 항목 목록을 조회하여 화면에 출력한다.
resetFilter()
public
검색 조건을 초기화하고 전체 항목 목록을 다시 출력한다.
fillTable()
private
조회된 체크리스트 항목 목록을 테이블에 출력한다.
showMessage()
private
조회 결과 없음 또는 오류 메시지를 화면에 출력한다.
- 시퀀스 다이어그램(체크리스트 빌더를 통한 항목 등록 / 조회)

설계 시퀀스
다이어그램 ID
설계 서브시스템 ID
유스케이스 명
체크리스트 빌더를 통한 항목 등록 / 조회
서브시스템 명
일상점검 관리

### 4.7 점검 관리(정기 / 정밀 / 수시)
- 클래스 다이어그램

서브시스템 ID
서브시스템 명

- Entity 클래스

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Entity
Entity ID
InspectionCategory
클래스명
점검분야 분류 / InspectionCategory
설명
정기점검, 정밀안전진단, 수시점검에서 사용하는 점검분야 분류 정보를 담은 클래스
지속성
Persistence

변수명
가시성
자료형
설명
categoryCode
private
String
점검분야 분류 고유 코드
categoryName
private
String
점검분야 분류명
categoryDetail
private
String
점검분야 상세 설명
useYn
private
boolean
점검분야 사용 여부
createdAt
private
DateTime
점검분야 등록 일시

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Entity
Entity ID
InspectionCategoryStatusData
클래스명
점검분야 분류 현황 데이터 / InspectionCategoryStatusData
설명
점검분야 분류 조회 시 검색 조건에 따라 출력되는 점검분야 목록 및 상태 정보를 담은 클래스
지속성
Persistence

변수명
가시성
자료형
설명
searchKeyword
private
String
조회 시 입력한 검색어
categoryCode
private
String
점검분야 분류 코드
categoryName
private
String
점검분야 분류명
categoryDetail
private
String
점검분야 상세 설명
useYn
private
boolean
점검분야 사용 여부
resultCount
private
int
조회 결과 건수
- 테이블 명세

Entity Id
Entity
Entity 명
InspectionCategory
필드명
동의어
자료 형식
자료 크기
NOT NULL
PK
FK
categoryCode
분류코드
VARCHAR
20
Y
Y
categoryName
분류명
VARCHAR
100
Y
categoryDetail
상세설명
VARCHAR
300
useYn
사용여부
BOOLEAN
1
Y
createdAt
등록일시
DATETIME
8
Y

Entity Id
Entity
Entity 명
InspectionCategoryStatusData
필드명
동의어
자료 형식
자료 크기
NOT NULL
PK
FK
searchKeyword
검색어
VARCHAR
100
categoryCode
분류코드
VARCHAR
20
Y
categoryName
분류명
VARCHAR
100
Y
categoryDetail
상세설명
VARCHAR
300
useYn
사용여부
BOOLEAN
1
Y
resultCount
조회건수
INT
4
Y
- DB 상세 설계 명세

관련 Entity
테이블 명
InspectionCategory
1) 점검분야 분류 등록
INSERT INTO InspectionCategory (
categoryCode,
categoryName,
categoryDetail,
useYn,
createdAt
) VALUES (
?, ?, ?, TRUE, NOW()
);
2) 점검분야 분류 코드 중복 확인
SELECT
categoryCode
FROM InspectionCategory
WHERE categoryCode = ?
AND useYn = TRUE;
3) 점검분야 분류 전체 조회
SELECT
categoryCode,
categoryName,
categoryDetail,
useYn,
createdAt
FROM InspectionCategory
WHERE useYn = TRUE
ORDER BY createdAt DESC;
4) 점검분야 분류 조건 조회
SELECT
categoryCode,
categoryName,
categoryDetail,
useYn,
createdAt
FROM InspectionCategory
WHERE useYn = TRUE
AND (
categoryName LIKE CONCAT('%', ?, '%')
OR categoryCode LIKE CONCAT('%', ?, '%')
)
ORDER BY createdAt DESC;
5) 사용 중인 점검분야 분류만 조회
SELECT
categoryCode,
categoryName,
categoryDetail,
useYn,
createdAt
FROM InspectionCategory
WHERE useYn = TRUE
ORDER BY categoryName;
- Control 클래스

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Control
클래스명
점검분야 분류 관리 서비스 / InspectionCategoryManagementService
설명
점검분야 분류 등록, 코드 중복 확인, 필수값 검증, 조건별 조회 등 점검분야 분류 등록/조회 비즈니스 로직을 담당하는 클래스
지속성
Transient

오퍼레이션명
가시성
파라미터 / 반환값
설명
validateCategory()
public
InspectionCategory / Boolean
점검분야 분류명 등 필수 입력값 누락 여부를 검사한다.
checkDuplicateCode()
public
String / Boolean
입력한 점검분야 분류 코드의 중복 여부를 확인한다.
registerCategory()
public
InspectionCategory / Boolean
새로운 점검분야 분류 정보를 등록한다.
getCategoryList()
public
String / List
검색 조건에 맞는 점검분야 분류 목록을 조회한다.
getActiveCategoryList()
public
- / List
사용 중인 점검분야 분류 목록만 조회한다.

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Control
클래스명
점검분야 분류 저장소 / InspectionCategoryRepository
설명
점검분야 분류 데이터의 DB 저장, 중복 확인, 조건 조회를 담당하는 클래스
지속성
Transient

오퍼레이션명
가시성
파라미터 / 반환값
설명
save()
public
InspectionCategory / Boolean
점검분야 분류 정보를 DB에 저장한다.
existsByCode()
public
String / Boolean
동일한 점검분야 분류 코드가 존재하는지 확인한다.
findByCondition()
public
String / List
검색 조건에 맞는 점검분야 분류 목록을 조회한다.
findAll()
public
- / List
전체 점검분야 분류 목록을 조회한다.
findActiveCategories()
public
- / List
사용 중인 점검분야 분류 목록을 조회한다.
- Boundary 클래스(점검분야 분류 등록)

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Boundary
클래스명
점검분야 분류 등록 View / InspectionCategoryRegisterView
설명
안전관리부서 담당자가 점검분야 분류 코드, 분류명, 상세 설명을 입력하여 신규 점검분야를 등록하는 UI 클래스
지속성
Transient

오퍼레이션
가시성
설명
InspectionCategoryRegisterView()
public
점검분야 분류 등록 화면을 생성하고 UI 초기화 및 이벤트를 설정한다.
initForm()
private
분류 코드, 분류명, 상세 설명 입력 폼을 초기화한다.
onRegister()
public
사용자가 입력한 점검분야 분류 등록 요청을 처리한다.
onCancel()
public
입력 중인 내용을 취소하고 이전 목록 화면으로 이동한다.
showMessage()
private
등록 완료 또는 오류 메시지를 화면에 출력한다.
clearForm()
private
입력 폼을 초기 상태로 리셋한다.
- 시퀀스 다이어그램(점검분야 분류 조회)

설계 클래스
다이어그램 ID
설계 서브시스템 ID
클래스 구분
Boundary
클래스명
점검분야 분류 조회 View / InspectionCategorySearchView
설명
안전관리부서 담당자가 분류명, 상태 등의 검색 조건을 입력하여 점검분야 분류 목록을 조회하는 UI 클래스
지속성
Transient

오퍼레이션
가시성
설명
InspectionCategorySearchView()
public
점검분야 분류 조회 화면을 생성하고 UI 초기화 및 이벤트를 설정한다.
initForm()
private
검색 필터와 전체 목록 출력 영역을 초기화한다.
updateList()
public
전체 점검분야 분류 목록을 조회하여 화면에 출력한다.
updateList(String condition)
public
검색 조건에 맞는 점검분야 분류 목록을 조회하여 화면에 출력한다.
fillTable()
private
조회된 점검분야 분류 데이터를 테이블에 출력한다.
showMessage()
private
조회 결과 없음 등의 메시지를 화면에 출력한다.
- 시퀀스 다이어그램(점검분야 분류 등록 / 조회)

설계 시퀀스
다이어그램 ID
설계 서브시스템 ID
유스케이스 명
점검분야 분류 등록 / 조회
서브시스템 명
점검 관리

## 4.8 안전교육 관리

> TODO: 안전교육관리 파트는 추후 SRS 및 구현 범위가 확정된 뒤 상세 설계를 추가한다.

### 작성 예정 항목

- [ ] 클래스 다이어그램 작성
- [ ] 서브시스템 ID 및 서브시스템 명 확정
- [ ] Entity 클래스 명세 작성
- [ ] 테이블 명세 작성
- [ ] DB 상세 설계 명세 작성
- [ ] Control 클래스 명세 작성
- [ ] Boundary 클래스 명세 작성
- [ ] 시퀀스 다이어그램 작성
- [ ] SRS의 안전교육관리 식별자 중복 정리 후 SDD 식별자와 매핑
- [ ] 요구사항 추적표에 안전교육관리 항목 반영

### 참고 예정 유스케이스

- 연구활동종사자 및 이수기준 등록/수정/삭제/조회
- 안전교육일지 등록/조회/수정/삭제
- 교육이수 결과 등록/조회/삭제
- 수료증 발급/조회
- 학습 결과 등록/조회
