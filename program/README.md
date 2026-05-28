# Lab Safety Management Program

`program` 폴더는 연구실 안전관리 시스템의 Java 21 구현체다. 현재 구현은 콘솔 기반 모듈형 모놀리식 구조로 시작하며, 기능은 `user`, `laboratory`, `chemical`, `waste`, `inspection`, `education` 모듈로 분리한다.

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
