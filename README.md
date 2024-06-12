# basic-springboot-2024
Java 빅데이터 개발자 과정 Spring Boot 학습 리포지토리

## 1일차
- Spring Boot 개요
    - 개발 환경, 개발 난이도를 낮추는 작업
    - Servlet > EJB > JSP > Spring > SpringBoot
    - 장점
        - Spring의 기술을 그대로 사용 가능(migration 간단)
        - JPA를 사용하면 ERD나 DB 설계를 하지 않고도 손쉽게 DB 생성 가능
        - Tomcat Webserver가 내장(따로 설치 필요 없음)
        - 서포트 기능 다수 존재(개발 쉽게 도와줌)
        - JUnit 테스트, Log4J2 로그도 모두 포함
        - JSP, **Thymeleaf**, Mustache 등 편하게 사용 가능
        - DB 연동이 쉬움
    
    - MVC
        <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/sp002.png?raw=true" width="730">

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
            - VS Code 재시작
            - build.gradle SpringBoot Framework 버전을 다운 3.3.0 -> 3.1.5

    - 프로젝트 생성 후
        - /build.gradle 확인
        - src/main/application.properties(또는 .yml) 확인
        - src/java/groupid/arifactid/Java 소스 파일 위치, 작업
        - src/main.resources/ 프로젝트 설정 파일, 웹 리소스 파일(css, js, html, jsp 등)
        - Spring01Application.java Run | Debug 메뉴
        - Gradle build
            - 터미널에서 .\gradlew.bat 실행
            - Gradle for java 설치한 경우
                - Gradle 아이콘(코끼리) > Tasks > build > build play icon(Run task) 실행
        - Spring Boot Dashboard
            - Apps > Spring01 Run | Debug 중에서 하나 아이콘 클릭해서 서버 실행
            - 디버그로 실행해야 Hot code replace가 동작

                <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/sp001.png?raw=true" width="350">

        - 브라우저 변경 설정
            - 설정(Ctrl + ,) > browser > Spring > Dashboard Open With 'Internal' -> 'external'로 변경
            - Chrome을 기본 브라우저로 사용 추천


## 2일차
- Oracle 도커로 설치
    - 설치되어 있는 Oracle 삭제
    
- Database 설정
    - H2 DB - Spring Boot에서 손쉽게 사용한 내장된 Inmemory DB, Oracle, MySQL, SQLServer와 쉽게 호환됨
    - Oracle - 운영 시 사용할 DB
    - MySQL - Optional 설명할 DB
    - Oracle PKNUSB / pknu_p@ss로 생성
        - 콘솔
        ```shell
        > sqlplus system/password
        ```