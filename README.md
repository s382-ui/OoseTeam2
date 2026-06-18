# 연구실 안전관리 시스템

React 프론트엔드와 Java Spring Boot 백엔드로 구성된 연구실 안전관리 시스템이다.

```text
OoseTeam2/
├─ frontend/   React 화면과 REST API 호출
├─ backend/    Java 업무 로직과 JSON 저장
└─ docs/       설계 및 실행 문서
```

## 실행

터미널 1에서 Java 백엔드를 실행한다.

```powershell
cd backend
.\gradlew.bat bootRun
```

터미널 2에서 React 프론트엔드를 실행한다.

```powershell
cd frontend
npm install
npm start
```

- React: `http://localhost:3000`
- Java API: `http://localhost:8080/api`

자세한 내용은 `docs/run-guide.md`를 확인한다.
