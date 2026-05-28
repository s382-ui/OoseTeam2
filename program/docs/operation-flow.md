# Operation Flow

## 1. 시작 흐름

```mermaid
sequenceDiagram
    participant App as LabSafetyApplication
    participant Boot as ApplicationBootstrap
    participant Menu as MainMenu
    participant Mod as FeatureModule

    App->>Boot: create()
    Boot->>Menu: build main menu
    Menu->>Menu: print available modules
    Menu->>Mod: open selected module
    Mod->>Mod: run module menu loop
```

## 2. 동작 방식

1. `LabSafetyApplication`이 시작된다.
2. `ApplicationBootstrap`이 콘솔과 모듈 목록을 만든다.
3. `MainMenu`가 모듈 목록을 사용자에게 보여준다.
4. 사용자가 모듈을 선택하면 해당 모듈 메뉴로 이동한다.
5. 모듈 메뉴는 개요, 목록 조회, 신규 등록, 데모 데이터 적재, 전체 삭제를 처리한다.
6. 뒤로가기를 선택하면 메인 메뉴로 돌아온다.

## 3. 유지보수 포인트

- 메뉴 문구를 바꾸려면 `ui/MainMenu`를 수정한다.
- 모듈별 입력 항목을 바꾸려면 해당 모듈의 `presentation`을 수정한다.
- 요약 표시 문자열은 도메인 record의 `summary()`를 수정한다.
- 저장 정책을 바꾸려면 `infrastructure`를 교체한다.
