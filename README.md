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
        - JUnit 테스트, Log4j2 로그도 모두 포함
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
    - 윈도우 서비스 내(services.msc)에 Oracle 관련 서비스 종료
    - Docker에서 Oracle 이미지 컨테이너를 다운로드 후 실행
    - Docker 설치 시 오류 Docker Desktop - WSL Update failed
        - Windows 업데이트 실행 최신판
        - https://github.com/microsoft/WSL/releases wsl.2.x.x.x64.msi 다운로드
        - Docker Desktop 재실행
    - Oracle 최신판 설치
    ```shell
    > docker --version
    Docker version 26.1.1, build 4cf5afa
    > docker pull container-registry.oracle.com/database/free:latest
    latest: ....
    ...: Download complete
    > docker images
    REPOSITORY                                    TAG       IMAGE ID       CREATED       SIZE
    container-registry.oracle.com/database/free   latest    7510f8869b04   7 weeks ago   8.7GB
    > docker run -d -p 1521:1521 --name oracle container-registry.oracle.com/database/free
    ....
    > docker logs oracle
    #########################
    DATABASE IS READY TO USE!
    #########################
    > docker exec -it oracle bash
    bash-4.4$
    ```

    - oracle system 사용자 비번 oracle로 설정
    ```shell
    bash-4.4$ ./setPassword.sh oracle
    ```

    - Oracle 접속 확인
        - DBeaver 탐색기 > Create > Connection
    
- Database 설정
    - Oracle - 운영 시 사용할 DB
    - Oracle PKNUSB / pknu_p@ss로 생성
        - 콘솔(도커/일반 Oracle)
        ```shell
        > sqlplus system/password
        SQL> select name from v$database;
        // 서비스명 확인
        // 최신 버전에서 사용자 생성 시 C## prefix 방지 쿼리
        SQL> ALTER SESSION SET "_ORACLE_SCRIPT"=true;
        // 사용자 생성
        SQL> create user pknusb identified by "pknu_p@ss";
        // 사용자 권한
        SQL> grant CONNECT, RESOURCE, CREATE SESSION, CREATE TABLE, CREATE SEQUENCE, CREATE VIEW to pknusb;
        // 사용자 계정 테이블 공간 설정, 공간 쿼터 할당
        SQL> alter user pknusb default tablespace users;
        SQL> alter user pknusb quota unlimited on users;
        ```

    - H2 DB - Spring Boot에서 손쉽게 사용한 내장된 Inmemory DB, Oracle, MySQL, SQLServer와 쉽게 호환됨
    - MySQL - Optional 설명할 DB

- Spring Boot + MyBatis
    - application name : spring02
    - Spring Boot 3.3.x에는 MyBatis 없음
    - Dependency 중 DB(H2, Oracle, MySQL)가 선택되어 있으면 웹서버 실행 안됨

    - build.gradle 확인
    - application.properties 추가 작성
    ```properties
    spring.application.name=spring02

    ## 포트 번호 변경
    server.port=8091

    ## 로그 색상
    spring.output.ansi.enabled=always

    ## 수정사항이 있으면 서버 자동 재빌드 설정
    spring.devtools.livereload.enabled=true
    spring.devtools.restart.enabled=true

    ## 로그 레벨
    logging.level.org.springframework=info
    logging.level.org.zerook=debug

    ## Oracle 설정
    spring.datasource.username=pknusb
    spring.datasource.password=pknu_p@ss
    spring.datasource.url=jdbc:oracle:thin:@localhost:11521:FREE
    spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

    ## MyBatis 설정
    ## mapper 폴더 밑에 여러가지 폴더 내재, 확장자는 .xml이나 파일명은 뭐든 상관없음
    mybatis.mapper-locations=classpath:mapper/**/*.xml
    mybatis.type-aliases-package=com.hyanyul.spring02.domain
    ```

    - MyBatis 적용
        - SpringBoot 이전 resource/WEP-INF 위치에 root-context.xml에 DB, MyBatis 설정
        - SpringBoot 이후 application.properties + Config.java로 변경

## 4일차
- Spring Boot JPA + Oracle + Thymeleaf + React
    - JPA -> DB 설계를 하지 않고 엔티티 클래스를 DB로 자동생성해주는 기술, Query로 만들 필요 없음
    - H2 -> Oracle, MySQL, SQLServer 등과 달리 Inmemory DB, 스프링부트 실행되면 같이 실행되는 DB
            개발 편의성, 다른 DB로 전환 시 아주 편리, 개발하는 동안 사용 추천
    - Thymeleaf -> JSP의 단점 복잡한 템플릿 형태 + 스파게티 코드 해소(간결화, 완전히 없어진 건 아님)해주는 템플릿
    - Bootstrap -> 웹디자인 및 CSS의 혁신, 커스터마이징 가능
    - 소셜로그인 -> 구글, 카카오, 네이버 등 소셜로 로그인할 수 있는 기능
    - React -> 프론트엔드 분리, 백엔드 서버와 프론트엔드 서버 따로 관리(통합 가능)

- Spring Boot JPA 프로젝트 생성
    - 명령 팔레트로 시작, Spring Initialzr: Create a Gradle Project...
    - Specify Spring Boot Version -> 3.2.6
    - Specify project language -> java
    - Input Group Id -> com.hyanyul
    - Artifact Id -> backboard
    - packaging type -> Jar
    - Java version -> 17
    - Dependency
        1. Spring Boot DevTools
        2. Lombok
        3. Spring Web
        4. Thymeleaf
        5. Oracle Driver(later)
        6. H2 Database(later)
        7. Data JPA(later)
    - Spring03 폴더 내에서 **Generate into this folder**

- Spring Boot JPA 프로젝트 개발 시작
    1. (설정) build.gradle dependency 확인
    2. (설정) application.properties 기본 설정 입력(포트 번호, 로그 색상, 자동 재빌드, 로그 레벨)
    3. MVC 패턴에 맞춰 각 기능별로 폴더를 생성(controller, service, entity, ...)
    4. /controller/MainContoller.java 생성, 기본 메소드 구현
    5. (설정) application.properties H2, JPA 설정 추가
    6. (설정) 웹 서버 실행 http://localhost:8080/h2-console DB 연결 확인

    7. /entity/Board.java 생성
        - GenerationType 타입
            - AUTO: SpringBoot에서 자동으로 선택(비추천)
            - IDENTITY: MySQL, SQLServer
            - SEQEUNCE: Oracle
        - column 이름을 createDate로 만들면 DB에 컬럼명이 create_date로 생성
            - 컬럼명에 _(언더바)를 안넣으려면 @column(name = "") 사용
    8. /entity/Reply.java 생성
    9. 두 엔티티 간 @OneToMany, @ManyToOne 설정
    10. 웹 서버 재시작 후 h2-console에서 테이블 생성 확인
    11. /repository/BoardRepository.java 빈 인터페이스(JpaRepository 상속) 생성
    12. /repository/ReplyRepository.java 빈 인터페이스(JpaRepository 상속) 생성
    13. (설정) application.properties ddl-auto=create -> ddl-auto=update 변경
    14. /test/.../repository/BoardRepositoryTests.java 생성, 테스트 메서드 작성
    15. 테스트 시작 > 웹서버 실행 > h2-console 확인

## 5일차
- Tip
    - Java Test 중 OpenJDK 64-Bit Server VM warning: Sharing is 빨간색 경고 뜨면
        - ctrl + , > Java Test Config 검색 > ssettings.json 편집
        ```json
        "java.test.config": {
            "vmArgs": [
                "-Xshare:off"
            ]
        }
        ```
        - 저장 후 실행

- Spring Boot 프로젝트 오류 처리
    - build를 해도 제대로 결과 반영되지 않으면
        - Github Remote Repository에 모두 커밋, 푸시
        - Local Repository를 모두 삭제 후 새로 커밋
        - 프로젝트 새로 로드, 초기화

- Spring Boot JPA 프로젝트 개발 계속
    1. jUnit 테스트로 CRUD 확인
    2. /service/BoardService.java 생성 후 getList() 메서드 작성
    3. /controller/BoardController.java 생성 후 /board/list 실행할 수 있는 메서드 작성
    4. /templates/board/list.html 생성
        - Thymeleaf 속성
            - th:if="${board != null}"
            - th:each="board : ${boardList}"
            - th:text"${board.title}"
    5. /service/BoardService.java에 getBoard() 메서드 추가
    6. /controller/BoardController.java에 /board/detail/{bno} 실행 메서드 작성
    7. /template/board/detail.html 생성

        <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/sp003.png?raw=true" width="730">

    8. /templates/board/detail.html에 댓글 영역 추가
    9. /service/ReplyService.java 생성, 댓글 저장 메서드 작성
    10. /controller/ReplyController.java 생성, /reply/create/{bno} postmapping 메서드 작성

    11. Bootstrap 적용
        - 다운로드 후 프로젝트에 위치
        - CDN 링크 추가
        - http://www.getbootstrap.com 다운로드 후 압축 해제
        - bootstrap.min.css, bootstrap.min.js를 templates/static에 위치
    12. /templates/board/list.html, detail.html 부트스트랩 적용

        <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/sp004.png?raw=true" width="730">


## 6일차
- Spring Boot JPA 프로젝트 개발 계속
    1. (설정) build.gradle Thymeleaf 레이아웃 사용을 위한 dependency 추가
    2. /templates/layout.html에 Thymeleaf로 레이아웃 템플릿 생성
    3. list.html, detail.htm 레이아웃 템플릿 적용
    4. /templates/layout.html Bootstrap CDN 적용
    5. /templates/board/list.html 게시글 등록 버튼 추가
    6. /templates/board/create.html 게시글 작성 페이지 생성
    7. /controller/BoardController.java create() GetMapping 메서드 작성
    8. /service/BoardService.java setBoard() 작성
    9. /controller/BoardController.java create() PostMapping 메서드 작성
    10. (문제) 아무 내용 안적어도 저장됨
    11. (설정) build.gradle 입력값 검증 Spring Boot Validation dependency 추가
    12. /validation/BoardForm.java 클래스 생성
    13. /controller/BoardController.java에 BoardForm 전달(Get, PostMapping 둘 다)
    14. create.html 입력 항목 name, id를 th:field로 변경(ex. th:field="*{title}")
    15. 댓글 등록에도 반영. ReplyForm, ReplyController, detail.html 작업
    16. detail.html 경고 영역 div는 create.html 복사해서 그대로 사용
    17. (문제) 각 입력창에 공백을 넣었을 때 입력되는 문제 @NotEmpty는 스페이스를 허용 -> @NotBlank로 변경

        <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/sp005.png?raw=true" width="730">


    18. 네비게이션바(navbar) 추가
    19. 테스트로 대량 데이터 추가

## 7일차
- Spring Boot JPA 프로젝트 개발 계속
    0. 개념
        ```sql
        -- Oracle 전용(11g 이하는 이 쿼리 동작 안함)
        select b1_0.bno,b1_0.content,b1_0.create_date,b1_0.title 
          from board b1_0 offset 0      -- 0부터 시작해서 페이지 사이즈만큼 증가
          rows fetch first 10 rows only -- 페이지 사이즈
        ```
    1. 페이징(중요)
        - /repository/BoardRepository.java에 findAll(pageable) 인터페이스 메서드 작성
        - /service/BoardService.java에 getList(page) 메서드 작성
        - /controller/BoardController.java에 list() 메서드 수정
        - /templates/board/list.html에 boardList -> paging 변경
        - /templates/board/list.html 하단 페이징 버튼 추가, thymeleaf 기능 추가
        - /service/BoardService.java getList() 최신순 역정렬로 변경
        - /templates/board/list.html에 게시글 번호 수정

            <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/sp006.png?raw=true" width="730">


    2. /templates/board/list.html td 뱃지 태그 추가

    3. H2 -> Oracle로 DB 변경
        - build.gradle, Oracle dependency 추가
        - application.properties Oracle 관련 설정 추가, H2 설정 주석 처리
        - 재시작
    
    4. 스프링 시큐리티(그 다음 중요)
        - (설정) build.gradle 스프링 시큐리티 관련 dependency 추가
        - (설정) Gradle 재빌드, 서버 실행
        - user / 로그 상 UUID 입력(서버 재실행할 때마다 바뀜)
        - /security/SecurityConfig.java 보안 설정 파일 생성 및 작성 -> 시큐리티 다시 풀어줌

        - /entity/Member.java 생성
        - /repository/MemberRepository.java 인터페이스 생성
        - /service/MemberService.java 생성, setMember() 메서드 작성

## 8일차
- Spring Boot JPA 프로젝트 개발 계속
    1. 스프링 시큐리티 계속
        - /security/SecurityConfig.java에 BCryptPasswordEncoder를 Bean으로 작업
        - /validation/MemberForm.java 생성
        - /controller/MemberController.java 생성
        - /entity/Member.java에 regDate 추가
        - /service/MemberService.java regDate() 부분 추가 작성
        - /templates/member/register.html 생성
        - (설정) Member 테이블에 저장된 회원정보 확인
        - /templates/layout.html 회원가입 링크 추가
        - /controller/MemberController.java PostMapping register에 중복회원가입 방지 추가
        - /security/MemberRole.java enum으로 ROLE_ADMIN, ROLE_USER 생성
        - /entity/Member.java role 변수 추가
       
            <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/sp007.png?raw=true" width="730">
    
    
    2. 로그인 기능
        - /security/SecurityConfig.java에 login url 설정
        - /templates/member/login.html 생성
        - /repository/MemberRepository.java find* 메서드 추가
        - /controller/MemberController.java login Get 메서드 작성
        - /service/MemberSercurityService.java - 로그인은 post를 사용하지 않고, Spring Security가 지원하는 UserDetailsService 인터페이스 활용
        - /security/SecurityConfig.java 계정 관리자 Bean 추가
        - /templates/layout.html 로그인/로그아웃 토글 메뉴 추가
    
    3. 게시글 작성자 추가
        - /entity/Board.java, /entity/Reply.java에 작성자 변수(속성) 추가
        - /service/MemberService.java에 getMember() 메서드 정의
        - (Tip) default Exception으로 예외를 처리하면 메서드 뒤에 항상 throws Exception을 적어줘야 함.
        - /common/NotFoundException.java 생성 -> throws Exception 쓰는데 반영
        - /service/ReplyService.java setReply() 사용자 추가
        - /controller/ReplyController.java 오류나는 setReply() 파라미터 수정
        - /service/BoardService.java .. 
        - /controller/BoardController.java setBoard() 사용자 추가
        - /controller/ 작성하는 Get/Post 메서드에 @PreAuthorize 어노테이션 추가
        - /config/SecurityConfig.java @PreAuthorize 동작하도록 설정 추가
        - /templates/board/detail.html 답변 textarea 로그인 전/후로 구분

        - /templates/board/list.html table 태그에 작성자 컬럼 추가
        - /templates/board/detail.html 게시글 작성자, 댓글 작성자 표시 추가

## 9일차
- Spring Boot JPA 프로젝트 개발 계속
    1. 수정, 삭제 기능
        - /entity/Board, Reply.java 수정일자 필드 추가
        - /templates/board/detail.html 수정, 삭제버튼 추가
            - sec:authorize="isAuthenticated()" 없으면 500 에러
        - /controller/BoardController.java에 modify() 메서드 작성
        - /templates/board/create.html form 태그의 th:action 삭제
            - create.html 생성, 수정할 때 모두 사용
            - get이 /board/create로 들어가면 post도 /board/create로 실행되고, /board/modify/{bno}로 들어가면 post도 같은 url로 실행
        - /service/BoardService.java에 수정 관련 메서드 추가 작성
        - /controller/BoardController.java, modify() POST 메서드 작성
            - html에는 BoardForm 객체 값이 들어있음. 컨트롤러에서 받아서 Board 객체 다시 만들어 서비스로 전달

        - /service/BoardService.java 삭제 관련 메서드 추가
        - /controller/BoardController.java delete() GET 메서드 작성

        - /templates/board/detail.html 댓글 수정, 삭제 버튼 추가
        - /service/ReplyService.java 수정, 삭제 관련 메서드 추가
        - /controller/ReplyController.java modify GET/POST 메서드, 삭제 GET 메서드 작성
        - /templates/reply/modify.html 생성

        - /templates/board/detail.html에 게시글, 댓글 수정 날짜 표시

    2. 앵커 기능
        - 추가, 수정, 삭제 시 이전 자신의 위치로 되돌아가는 기능
        - /templates/board/detail.html 댓글마다 앵커링 추가
        - /controller/ReplyController.java modify() POST 매핑 return에 앵커링 추가
        - /service/ReplyService.java 생성 메서드 변경
        - /controller/ReplyController.java create POST 메서드 변경

        - /controller/BoardController.java detail() 메서드 수정

    3. 검색 기능
        - /service/BoardService.java search() 메서드 추가
        - /repository/BoardRepository.java findAll() 메서드 추가
        - /service/BoardService.java getList() 메서드 추가 생성
        - /controller/BoardController.java list() 메서드 추가
        - /templates/board/list.html 검색창 추가, searchForm 폼 영역 추가, **페이징 영역 수정, JavaScript 추가**

## 10일차
- Spring Boot JPA 프로젝트 개발 계속
    1. 검색 기능 -> JPA Query
        - @Query 어노테이션으로 직접 쿼리 작성
        - 단순 쿼리가 아니라서 JpaRepository가 자동으로 만들어 줄 수 없을 때 사용
        - DB 표준 쿼리와 차이 있음(Java Entity와 일치시켜야 함)
        - /repository/BoardRepository.java에 findAllByKeyword() 메서드 추가
        - JPA Query @Query("")에 작성
        - /service/BoardService.java getList() 수정

    2. 마크다운 적용
        - Wysiwyg 에디터 
            - CKEditor(https://ckeditor.com/), TinyMCE
        - SimpleMDE(https://simplemde.com/) 
            - 깃허브에 CDN 링크 복사
            - layout.html 링크 추가
        - create.htm, detail.html, modify.html textarea id content를 SimpleMDE로 변환하는 JS 추가 
        
        - (설정) build.gradle 마크다운 디펜던시 추가
        - /common/CommonUtil.java 생성
        - /templates/board/detail.html 마크다운 뷰어 적용

            <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/sp009.png?raw=true" width="730">


            <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/sp010.png?raw=true" width="730">

    3. 카테고리 추가
        - /entity/Category.java 클래스 생성
        - /repository/CategoryRepository.java 인터페이스 생성
        - /service/CategoryService.java 생성
        - /entity/Board.java에 category 속성 추가
        - /service/BoardService.java getList(), searchBoard(), setBoard() 추가 생성
        - 카테고리를 자유게시판, 질문응답 게시판 분리
        - /templates/layout.html 사이드바 태그 추가 기입
        - /controller/BoardController.java CategoryService, GetMapping 메서드에 카테고리 매개변수 추가
        - /templates/list.html 카테고리 변수 추가
        - /controller/BoardController.java create() GET, POST 메서드에 category 추가

## 11일차
- Spring Boot JPA 프로젝트 개발 계속
    0. RestFul URL 이 잘못된 부분
        - /controller/MAinController.java main() 메서드 URL 변경

    1. 조회수 표시
        - /entity/Board.java 조회수 필드 추가
        - /service/BoardService.java hitBoard() 메서드 추가
        - /controller/BoardController.java detail()메서드 수정
        - /templates/board/list.html 조회수 컬럼 추가
        - DB를 Oravle -> H2

    2. (설정) AWS 사용
        - https://aws.amazon.com/ko/
        - (프리티어) 회원가입 및 로그인
            - 매뉴얼 참조(최근 내용으로 참조)
            - https://blogworks.co.kr/aws-%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85-%EB%A9%94%EB%89%B4%EC%96%BC/
        - (라이트세일) https://lightsail.aws.amazon.com/
            - 인스턴스 클릭 > 인스턴스 생성 버튼 클릭
            - 리전 서울
            - 인스턴스 이미지 > Linux/Unix
            - 블루프린트 > 운영 체제(OS) 전용 클릭 > Ubuntu 22.04 LTS
            - 인스턴스 플랜 > 듀얼 스택
            - 크기 선택 > 월별 $12 선택(무료 중 가장 스펙이 좋음)
            - 인스턴스 확인 > 본인이 원하는 이름으로 변경 후(Ubuntu-hyanyul)
            - 인스턴트 생성 클릭
            - 실행 중 확인 > ⁝ 클릭 > 관리
            - 네트워킹 > 고정 IP 연결 > 아이피명 입력 > 생성
            - IPv4 방화벽 > 규칙 추가 > 8080 포트 추가
            - 계정 > SSH 키 > 기본 키 다운로드(*.pem) > 이름 / 위치 변경(옵션)
        - PuTTY AWS 리눅스 서버 연결
            - https://www.chiark.greenend.org.uk/~sgtatham/putty/latest.html 64비트 다운로드
            - PuTTYgen 실행 > Load 기본키 선택 > Save private key 클릭 > .ppk로 저장
            - PuTTY 실행
                - Host Name: AWS 고정 아이피 입력
                - Connection > SSH > Auth > Credentials: Private Key를 .ppk로 선택
                - session > Saved session명 입력 > Save
                - Open 후 콘솔 login as: ubuntu 입력
        - FileZilla로 FTP 연결
            - https://filezilla-project.org/download.php 다운로드
            - 사이트 관리자 열기
                - 새 사이트
                - 프로토콜: SFTP
                - 호스트: 고정 아이피 입력
                - 로그온 유형: 키 파일
                - 사용자: ubuntu
                - 키 파일: *.ppk
                - 연결
        - PuTTY 설정 변경
            ```shell
            > sudo ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime   (한국 시간대로 변경)
            > hostname
            > sudo hostnamectl set-hostname hyanyul
            > sudo reboot   (서버 재시작)

            > sudo apt-get update   (전체 서버 패키지 업데이트)
            > java
            > sudo apt-get install openjdk-17-jdk
              Do you want to continue?[Y/n] y
            > java -version
              openjdk version "17.0.11" 2024-04-16
              OpenJDK Runtime Environment (build 17.0.11+9-Ubuntu-122.04.1)
              OpenJDK 64-Bit Server VM (build 17.0.11+9-Ubuntu-122.04.1, mixed mode, sharing)
            ```
        - VSCode
            - Gradle for java > Tasks > build > bootJar
            - *-SNAPSHOT.jar 생성 확인
        - FileZilla
            - *.jar > AWS로 전송
        - PuTTY 
            ```shell
            > ls
            bootserver
            > cd bootserver
            > ls
            backboard-1.0.1-SNAPSHOT.jar
            > java -jar backboard-1.0.1-SNAPSHOT.jar
            <!-- sudo java -jar backboard-1.0.1-SNAPSHOT.jar 로 실행 시 슈퍼 유저로 접속 -> local.mv.db가 안만들어짐 -->
            ```
         - 스프링부트서버 백그라운드 실행 쉘 작성
         - > nano start.sh
            ```shell
            #!/bin/bash

            JAR=backboard-1.0.2-SNAPSHOT.jar
            LOG=/home/ubuntu/bootserver/backbord_log.log

            nohup java -jar $JAR > $LOG 2>&1 &
            ```
         - 파일권한 바꾸기(실행가능)
            ```shell
            > chmod +x start.sh
            ```

         - > nano stop.sh
            ```shell
            #!/bin/bash

            BB_PID=$(ps -ef | grep java | grep backboard | awk '{print $2}')

            if [ -z "$BB_PID" ];
            then
               echo "BACKBOARD is not running"
            else
               kill -9 $BB_PID
               echo "BACKBOARD terminated!"
            fi
            ```
         - 파일권한 바꾸기(실행가능)
            ```shell
            > chmod +x stop.sh
            ```
         
         - 서버실행

    <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/sp011.png?raw=true" width="730">


## 12일차
- Spring Boot JPA 프로젝트 개발 계속
    1. 에러페이지 작업(404, 500, 그 외)
        - application.properties 에러페이지 관련 설정 추가
        - resource/static/img/bg_error.jpg 저장
        - resource/templates/404.html, 500.html, error.html 페이지 생성
        - /controller/CustomErrorController.java 생성

    2. 비밀번호 초기화
        - build.gradle 메일 보내기 위한 dependency 추가
        - application.properties 메일 설정(네이버) 입력
        - 네이버 메일 SMTP 설정 > 환경설정 > POP3/IMAP 설정

            <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/sp012.png?raw=true" width="730">

        - /config/SecutiryConfig.java CSRF 설정 변경
        - /service/MailService.java 생성
        - /restcontroller/MailController.java 생성
        - https://www.postman.com/ 다운로드

## 13일차
- Spring Boot JPA 프로젝트 개발 계속
    0. 메일 작업 중 생긴 오류
        - 로그인 후 글 작성 시 500 에러 발생
        - CSRF 토큰때문에 발생하는 오류
        - /board/create.html, reply/modify.html에 있는 csrf관련 태그 주석 처리

    1. 비밀번호 초기화(계속)
        - /templates/member/login.html 비밀번호 초기화 버튼
        - /controller/MemberController.java reset() 메서드 추가
        - /templates/member/reset.html 생성 -> register.html 가져와서 수정
        - /controller/MailController.java 생성, /mail/reset-mail GET 매핑 메서드 생성
        - /service/MemberService.java에 메일 주소로 검색하는 getMemberByEmail() 메서드 추가
        - /service/MailService.java에 메일 전송 메서드 생성, 수정
            - UUID 생성해서 메일로 전송하는 기능 추가
            
            <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/sp013.png?raw=true" width="730">

        - /entity/Reset.java 생성
        - /repository/ResetRepository.java 인터페이스 생성, findByUuid() 추가
        - /service/ResetService.java 생성
        - /service/MailService.java에 ResetService 객체 생성, 메일 전송 후 setReset() 사용
        - /controller/MamberController.java, /mail/reset-password Get 메서드 작성
        - /templates/member/newpassword.html 생성
        - /controller/MemberController.java, /member/reset-password Post 메서드 작성
        - /service/MemberService.java에 setMember() 메서드 추가

            <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/sp014.png?raw=true" width="730">


## 14일차
- 리액트 개요
    - 서버 -> 백엔드
    - 클라이언트 사이드 -> 프론트엔드
    - 프론트엔드: html + css + js(html, jsp, aspx, php 등 확장자 다양)
    - js만 가지고 프론트엔드 만들어보자 -> 리액트
    - css는 있어야 함
    - 페이스북이 자기 웹페이지 프론트를 좀 더 개선해보고자 개발 시작
    - 리액트는 기본적으로 SPA(Single Page Application)을 목적으로 만들어짐
    - Node.js 서버 사이드 js를 사용해서 서버를 동작시킴
    - 패키지 매니저 종류: npm, chocolatey, yarn 등(본인이 필요한 거만 공부)

- 리액트 개발 순서
    1. Node.js 설치
        - https://nodejs.org/en, Download Node.js(LTS) 클릭
        - 설치 후 콘솔에서 node --version으로 확인. 현재 v20.15.0

    2. 리액트 패키지 설치
        - > npm uninstall -g create-react-app
        - > npm install -g create-react-app

    3. 리액트 프로젝트를 초기화
        - VS Code에서 터미널 오픈
        - npx create-react-app basic-app

    4. 리액트 실행
        - 콘솔에서 위에서 만든 프로젝트앱 이름까지 진입 basic-app()
        - 웹브라우저 http://localhost:3000 서버 확인
        - node가 3000 포트로 웹서버를 실행
        - 웹서버가 실행된 상태에서 개발하는 것이 가장 좋음
        - 모든 웹페이지는 index.html(jsp, php 등)이 가장 처음 화면, App.js가 메인 개발 부분
    
- 리액트 기본 구조 및 개발 방법
    1. 깃헙 .gitignore에 react(node) 관련 설정 내용 추가
    2. 깃헙 .gitignore 먼저 커밋하고 푸시

    3. src/App.css, App.js, index.js 파일만
    4. javascript이기 때문에 js 위주로 개발
    5. App.js부터 개발 시작하면 됨
    
- 리액트 기초 공부
    1. html 태그처럼 개발자가 새로운 요소(객체) 생성 가능
        ```jsx
        function CustomButton(){    // CustonButton 객체 생성
        return (
            <button>My Button</button>
        );
        }
        ```
    
    2. /component/CustomButton.js 생성, 위 소스 옮김
        - 같은 파일이 아닌 곳에 객체를 만들면
        - 가져와 쓰기 위해서 export default 객체 이름; 필수

    3. React 문법은 JSX. 일반 js와 조금 차이 있음
        - className은 JSX에만 존재
        - HTML에 있던 class는 JSX에서 className으로 변경
        - 인라인으로 style 쓸 때 CSS 명칭 다름
        - 대신, *.css 파일로 작업 시 기존 그대로 사용해도 무방
        - JSX 문법에는 모든 요소는 상위 태그 하나에 속해야 함
        - https://transform.tools/html-to-jsx 참조할 것
    
    4. 데이터 화면에 표시
        - 변수 생성 시 const 많이 사용
        - 변수 사용 시 중괄호({}) 사이에 입력
        - CSS를 *.css 파일에 작성할 때는 html에서 사용할 때와 동일
            - ex. border-radius: 50%;
        - JSC에 사용할 때는 
            - ex.borderRadius: '50%'
        - 리액트에서 css 쓸 때는 *.css 파일로 작업할 것

    5. 조건부 렌더링
        ```jsx
        function CustomButton(){
            let isLoggedIn = true;  // 로그인 여부
            let content;

            if(isLoggedIn){
                content = <button>Log Out</button>;
            } else{
                content = <button>Log In</button>;
            }

            return (
                <>
                    {content}
                </>
            );
        } 
        ```
        - 또는 
        ```jsx
        function CustomButton(){
            return (
                <>
                    {
                        isLoggedIn ? (
                            <button>Log Out</button>
                        ) : (
                            <button>Log In</button>
                        )
                    }
                </>
            );
        } 
        ```

    6. 목록 표시
        - for, map() 함수 많이 사용
        - map() 사용 시 for문보다 짧게 구현 가능
        - 각 child 요소마다 key 속성 필요(없으면 경고)

            <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/react001.png?raw=true" width="730">

    7. 이벤트 핸들링
        - form + onSubmit, tag + onClick
        - 이벤트 파라미터 전달
        - 파라미터 필요해서 함수 뒤에 () 사용 시 이벤트 순서에 따라 리플레쉬 후 자동실행 됨
        - onClick = {() => function()} 람다식으로 변경 필요

            <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/react002.png?raw=true" width="730">
    
    8. 컴포넌트 간 데이터 전달
        - props 속성
        - props.속성이름.key이름

    9. 화면 업데이트
        - useState : 앱 화면의 상태를 기억하고, 사용하기 위한 객체
        - import { userState } from 'react'; 필수
        - const [count, setCount] = useState(0);

    10. Hooks
        - use로 시작하는 함수를 Hooks라 호칭, State, Effect 외에는 잘 안씀
        - useState : React에서 컴포넌트 상태를 추가, 보관
        - useEffect : 컴포넌트에서 사이드 이펙트를 수행할 때 사용
        - 기타 : useContext, useReducer, useCallback, useRef 등

- 리액트 추가 내용
    1. 리액트 관련 프레임워크
        - Next.js - 풀스택 React 프레임워크
        - Gatsby - 정적 사이트 React 프레임워크   
        - React Native - Android, iOS 멀티플랫폼 모바일 프레임워크

    2. npm으로 추가 라이브러리 설치
        - > npm install react react-dom

    3. VS Code 확장
        - ES7 + React/Redux/React-Native 설치
        - VSCode React Refactor : 리팩팅 도구 
        - Import Cost : 라이브러리 비용 계산
        - Simple React Snippets

    4. 리액트 개발자 도구
        - 크롬. 엣지 브라우저별로 따로 존재
        - React Developer Tools 설치


## 15일차
- Spring Boot React 연동 프로젝트 개발 계속
    1. 리액트 프로젝트 생성
        - 터미널 /spring03으로 이동
        - > npx create-react-app frontboard

    2. Spring Boot / React 같이 개발할 때
        - Spring Boot 웹서버 실행
        - React 프론트 웹서버 실행


    3. 리액트 라이브러리 설치, npm
        - 리액트용 Bootstrap 설치
        - > npm install react-bootstrap bootstrap -> css 디자인
        - **TIP npm audix fic --force는 절대 하지 말 것**
        - > npm install axios - REST API 통신 라이브러리 -> 리액트 화면 네비게이션
        - > npm install react-router-dom
        - > npm install react-js-pagination -> 리액트 페이징 처리

    4. frontboard 개발 시작
        - App.js, logo.svg 삭제, react-router-dom으로 Routes, Route 사용
        - index.js, reportWebVitals() 삭제
        - index.js, <React.StrictMode> 삭제 또는 주석
        - /src/layout/Header.js, Footer.js 생성
        - /src/routes/Home.js, BoardList.js, QnaList.js, Login.js 생성
        - App.js에 Route될 화면 추가
        - Header.js에 react-router-com 추가, Link, useNavigate 사용

    5. backboard Rest API 추가
        - /restcontroller/RestBoardController.java 생성, BoardController에 있는 메서드 복사
        - (문제) Spring Boot와 Rest API 간의 리턴 데이터 차이때문에 100% 호환 안됨
        - (문제) Spring Boot에서 만든 Entity는 Board와 Reply 등의 OneToMany / ManyToOne의 관계가 JSON으로 변환할 때 문제 발생
        - /Entity를 그대로 사용하지 말고, RestAPI에서는 다른 클래스 만들어야 함
        - /dto/BoardDto.java 생성
        - /dto/ReplyDto.java 생성
        - /RestBoardController.java에 getList()를 Board Entity -> BoardDto로 변경
        - /sercurity/SecurityConfig.java CORS 설정 추가

    6. frontboard 개발 계속
        - /BoardList.js axios 관련 RestAPI 호출 내용 추가
        - 테이블 내용을 boardList.map() 10개 리스트 출력

            <img src="https://github.com/hyanyul/basic-springboot-2024/blob/main/images/react003.png?raw=true" width="730">


## 16일차
- Spring Boot React 연동 프로젝트 개발 계속
    1. Spring Boot 서버가 실행되지 않았을 때
        - 프론트 서버부터 시작하면 Uncaught runtime error 발생
        - axios request 예외 발생 -> try / catch로 wrapping 필요

    2. 페이징
        - (Backend) /dto/PagingDto.java 생성
        - (Backend) /dto/Header.java 생성
        - RestBoardController.java list() 수정 List<BoardDto> -> Header<List<BoardDto>>로 형 변환
        - (Front) /BoardList.js 변수 부분 수정
        - (Front) /common/CommonFunc.js 생성 - 작성일 수정 함수 formatDate() 작성
        - /BoardList.js 날짜 부분에 formatDate() 적용
        - /BoardList.js 댓글 개수 표시
        - (Back) /dto/BoardDto.java 게시글 번호 변수 추가
        - RestBoardController.java 게시글 번호 계산 로직 추가
        - (Front) /BoardList.js bno를 num으로 변경

    3. 상세 화면
        - (Back) RestBoardController.java detail() 메서드 생성
        - (Front) /BoardList.js 제목 수정
        - /routes/BoardDetail.js 생성

## 17일차
- Spring Boot React 연동 프로젝트 개발 계속
    0. npm -> node에서 만든 package manager
        - yarn, brew(MacOS), chocolatey 등
        - node, npm, npx 등 명령어 먼저 공부

    1. 로그인
        - (Front) /layout/Header.js 로그인, 회원가입 버튼으로 변경
        - Login.js 화면 수정
        - (Back) RestMemberController.java 생성 login() Post 메서드 작성
        - MemberService.java, getMemberByUsernameAndPassword() 메서드 작성
        - Postman에서 테스트
        - (Front) Login.js axios 부분 작성
        - Home.js, localStorage 사용해서 로그인 정보 출력
        - Header.js 로그인 버튼 -> 로그아웃

    2. 상세화면 완료
        - (Back) RestBoardController.java detail() 리턴값 변경
        - (Front) BoardDetail.js에서 axios로 가져오기

    3. 입력화면