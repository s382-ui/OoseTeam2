# 실행 가이드

## 1. 목적

이 문서는 `program` 폴더의 연구실 안전관리 시스템을 실제로 실행하는 방법을 정리한다. 현재 프로젝트는 Java 21 기반의 콘솔 애플리케이션이며, 빌드 도구는 Gradle을 사용한다.

## 2. 실행 전 확인 사항

- JDK 21 이상이 설치되어 있어야 한다.
- `JAVA_HOME`이 올바르게 설정되어 있어야 한다.
- Gradle CLI가 설치되어 있으면 가장 간단하게 실행할 수 있다.
- Gradle CLI가 없어도 `javac`와 `java`만 있으면 수동 실행이 가능하다.

확인 명령 예시:

```powershell
java -version
javac -version
gradle -v
```

## 3. 권장 실행 방법

Gradle CLI가 설치된 경우 다음 순서로 실행한다.

```powershell
Set-Location C:\Users\tjdgns\OneDrive\Desktop\3-1\Object-Oriented_SoftwareEngineering\OOSE\program
gradle run
```

실행되면 메인 메뉴가 표시되고, 메뉴 번호를 입력해 각 기능 모듈로 이동할 수 있다.

## 4. Gradle이 없는 경우의 실행 방법

현재 워크스페이스에서 실제로 검증한 방식은 `javac`와 `java`를 직접 사용하는 방법이다.

```powershell
Set-Location C:\Users\tjdgns\OneDrive\Desktop\3-1\Object-Oriented_SoftwareEngineering\OOSE\program
Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue
Get-ChildItem -Recurse -Filter *.java src\main\java | ForEach-Object { $_.FullName } | Set-Content sources.txt
javac --release 21 -d out "@sources.txt"
java -cp out com.oose.labsafety.LabSafetyApplication
```

이 방식은 빌드 설정이 올바른지 빠르게 확인할 때 유용하다.

## 5. 실행 후 동작 방식

프로그램을 실행하면 다음 메뉴가 표시된다.

- 사용자 관리
- 연구실 관리
- 화학물질 관리
- 폐기물 관리
- 점검 관리
- 안전교육 관리

각 모듈에서는 공통적으로 다음 기능을 제공한다.

- 개요 보기
- 목록 보기
- 신규 등록
- 데모 데이터 적재
- 전체 삭제
- 뒤로가기

## 6. 종료 방법

메인 메뉴에서 `0`을 입력하면 프로그램이 종료된다.

## 7. 자주 보는 오류

- `gradle` 명령을 찾을 수 없으면 Gradle CLI가 설치되지 않은 것이다. 이 경우 수동 실행 방법을 사용하거나 Gradle Wrapper를 추가한다.
- `javac`가 `21`을 지원하지 않으면 JDK 버전을 확인한다.
- 한글이 깨져 보이면 터미널 인코딩과 파일 인코딩을 UTF-8로 맞춘다.

## 8. 유지보수 팁

- 새 모듈을 추가하면 `ApplicationBootstrap`에 등록해야 메인 메뉴에 나타난다.
- 실행 메뉴 문구를 바꾸려면 `ui/MainMenu`를 수정한다.
- 모듈 내부 흐름을 바꾸려면 해당 모듈의 `presentation` 클래스를 수정한다.
