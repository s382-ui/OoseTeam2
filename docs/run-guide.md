# 실행 방법

작성일: 2026-06-11

## 전제 조건

- Node.js 18 이상 권장
- npm 사용 가능
- 프로젝트 루트: `C:\Users\tjdgns\OneDrive\Desktop\3-1\Object-Oriented_SoftwareEngineering\OOSE`

## 최초 실행

프로젝트 루트에서 의존성을 설치한다.

```powershell
npm install
```

개발 서버를 실행한다.

```powershell
npm start
```

Create React App 기본 설정에 따라 브라우저에서 다음 주소로 접속한다.

```text
http://localhost:3000
```

## 빌드 검증

배포용 정적 파일 생성이 가능한지 확인한다.

```powershell
npm run build
```

성공 시 `build/` 폴더가 생성된다. 이 폴더는 검증 산출물이므로 일반적으로 커밋하지 않는다.

## 현재 스크립트

`package.json` 기준으로 사용할 수 있는 스크립트는 다음과 같다.

```text
npm start  - 개발 서버 실행
npm run build  - 프로덕션 빌드 생성
```

현재 별도 테스트 스크립트는 정의되어 있지 않다.

## 문제 해결

`react-scripts`를 찾을 수 없다는 오류가 발생하면 의존성이 설치되지 않은 상태다.

```powershell
npm install
npm run build
```

`npm install` 후 취약점 경고가 출력될 수 있다. 의존성 업그레이드는 앱 동작과 잠금 파일에 영향을 줄 수 있으므로 별도 작업으로 분리해 검토한다.

## 테스트 제안

현재 테스트 정보가 없으므로 다음 테스트를 우선 추가한다.

- 메뉴 전환 테스트: 사이드바 메뉴 클릭 시 대응하는 관리 화면 제목이 렌더링되는지 확인한다.
- CRUD 플로우 테스트: 대표 화면에서 등록, 조회, 검색, 삭제 흐름이 상태에 반영되는지 확인한다.
