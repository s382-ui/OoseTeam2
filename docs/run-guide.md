# 실행 방법

작성일: 2026-06-18

## 실행 환경

- Java 21
- Node.js 18 이상
- npm

시스템 Gradle 설치는 필요하지 않다. 백엔드에 포함된 Gradle Wrapper를 사용한다.

## Java 백엔드 실행

프로젝트 루트에서 다음 명령을 실행한다.

```powershell
cd backend
.\gradlew.bat bootRun
```

정상 실행 시 API 주소는 `http://localhost:8080/api`이다.

## React 프론트엔드 실행

새 터미널을 열고 다음 명령을 실행한다.

```powershell
cd frontend
npm install
npm start
```

브라우저에서 `http://localhost:3000`으로 접속한다. 화면을 정상적으로 사용하려면 Java 백엔드가 함께 실행 중이어야 한다.

## 빌드 검증

백엔드:

```powershell
cd backend
.\gradlew.bat test
```

프론트엔드:

```powershell
cd frontend
npm run build
```

## JSON 데이터

초기 및 시연 데이터는 다음 위치에 있다.

```text
backend/src/main/resources/data/
```

백엔드가 처음 데이터에 접근하면 초기 JSON을 다음 실행 디렉터리로 복사한다.

```text
backend/data/
```

등록과 삭제는 `backend/data`의 JSON에 반영된다. 초기 상태로 되돌리려면 백엔드를 종료하고 `backend/data` 폴더를 삭제한 뒤 다시 실행한다.

`backend/data`, `frontend/build`, `frontend/node_modules`, `backend/build`, `backend/.gradle`은 자동 생성 파일이므로 Git에 커밋하지 않는다.
