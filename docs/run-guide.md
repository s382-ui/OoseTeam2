# 실행 방법

작성일: 2026-06-18

## 최초 설치

프로젝트 루트에서 의존성을 설치한다.

```powershell
cd C:\Users\최남규\OOSE_project\OoseTeam2
npm install
```

## 개발 서버 실행

```powershell
npm start
```

브라우저에서 `http://localhost:3000`으로 접속한다.

## 빌드 검증

```powershell
npm run build
```

성공하면 배포용 결과가 `build/`에 생성된다. 이 폴더는 자동 생성 결과이므로 Git에 커밋하지 않는다.

## 초기 JSON 다시 불러오기

프로그램은 해당 저장 키가 없을 때만 `public/data`의 JSON을 읽는다. 시연 데이터를 처음 상태로 되돌리려면 브라우저 개발자 도구에서 현재 사이트의 Local Storage를 삭제한 뒤 새로고침한다.

## 주요 명령

```text
npm install     의존성 설치
npm start       개발 서버 실행
npm run build   배포용 빌드 검증
git status      Git 변경사항 확인
```
