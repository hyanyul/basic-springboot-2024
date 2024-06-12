# basic-springboot-2024
Java 빅데이터 개발자 과정 Spring Boot 학습 리포지토리

## 1일차
- Spring Boot 개요
    - 

- Spring Boot 개발환경 설정
    - Java JDK 확인(17 이상 사용)
        - https://jdk.java.net/archive/
        - 시스템 속성 > 고급 > 환경변수 중 JAVA_HOME 설정
    - Visual Studio Code
        - VSCodeUserSetup-x64-1.90.0.exe 아님. 설치하지 말 것
        - VSCodeSetup-x64-1.90.0.exe 로 설치할 것
        - Extensions > Korean Language Pack for Visual Studio Code 설치
        - Extensions > Extension Pack for Java 설치
            - Debugger for Java 등 6개의 확장팩이 같이 설치됨
        - Extensions > Spring Boot Extension Pack 설치
            - Spring Initializr Java Support 등 3개 확장팩 같이 설치
        - Extensions > Gradle for Java 설치
    - Gradle build tool 설치 고려
        - https://gradle.org/releases/
    - Oracle latest version Docker  - 보류
 
 - Spring Boot 프로젝트 생성
    - 메뉴 보기 > 명령 팔레트(Ctrl + shift + p)
        - Spring Initializr: Create a Gradle Project...
        - Specify Spring Boot Version: 3.2.6
        - Specify project language: 
        - Input Group Id: com.hyanyul
        - Input Artifact Id: spring01(대문자 불가)
        - Specify packaging type: Jar
        - Specify Java Version: 17
        - Choose dependencies: Selected 0 dependencies
        - 폴더 선택 Diaglog 팝업: 원하는 폴더 선택, Generate.. 버튼 클릭
        - 오른쪽 하단 팝업에서 Open 버튼 클릭
        - Git 설정 옵션, Language Support for Java(TM) by Red Hat 설정 항상 버튼 클릭
    
    - TroubleShooting
        1. 프로젝트 생성이 진행되다가 Gradle Connnection error
            - Extension > Gradle for Java 제거 > VS code 재시작한 뒤 프로젝트 재생성
        2. Gradle build 시 버전 에러로 빌드가 실패
            - Gradle build tool 사이트에서 에러에 표시된 버전의 Gradle bt 다운로드 > 개발 컴퓨터에 설치
        3. ':compileJava' execution failed...
            - JDK 17... 에러 메시지
            - Java JDK 잘못된 설치: x86(32bit) x64bit 혼용 설치
            - eclipse adoptium jdk 17 새로 설치, 시스템 환경설정
            - build.gradle SpringBoot Framework 버전을 다운 3.3.0 -> 3.1.5
            - VS Code 재시작

    - 프로젝트 생성 후
        - /build.gradle 확인
        - src/main/application.properties(또는 .yml) 확인
        - src/java/groupid/arifactid/Java 소스 파일 위치, 작업
        - src/main.resources/ 프로젝트 설정 파일, 웹 리소스 파일(css, js, html, jsp 등)
        - Spring01Application.java Run | Debug 메뉴