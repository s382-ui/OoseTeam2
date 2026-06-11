# Agent Instructions

이 프로젝트에 진입하는 Claude, Codex 및 기타 AI 에이전트는 작업 전후 문서화를 필수로 수행한다.

## 필수 읽기

작업을 시작하기 전에 다음 문서를 읽는다.

- `docs/implementation-review.md`
- `docs/run-guide.md`
- `docs/agent-entry-documentation-policy.md`
- `docs/documentation-maintenance.md`
- `docs/reference_mdFileList/`의 SRS, SDD, PMP, architecture 문서

## 필수 행동

- 코드 변경 전 현재 구현과 기존 문서를 대조한다.
- 기능 요구사항 또는 설계 판단이 필요한 경우 `docs/reference_mdFileList/`의 원본 요구사항/설계 문서를 함께 확인한다.
- 코드 변경 후 관련 문서를 함께 갱신한다.
- 문서가 길어지거나 독립 주제가 생기면 `docs/documentation-maintenance.md` 기준에 따라 새 문서로 분리한다.
- 문서와 구현이 충돌하는 상태로 작업을 완료하지 않는다.
- 사용자가 만든 미커밋 변경은 되돌리지 않는다.
- 테스트를 실행했는지, 실행하지 못했다면 이유를 최종 응답에 남긴다.

## 완료 기준

기능 추가, 버그 수정, 리팩터링, UI 변경, 테스트 변경은 관련 문서 갱신이 끝났을 때만 완료로 간주한다.

상세 규칙은 `docs/agent-entry-documentation-policy.md`를 따른다.
