package com.hyanyul.backboard.entity;

import java.time.LocalDateTime;
import java.util.List;

// import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 게시판 board 테이블 entity
@Getter
@Setter
@Entity // 테이블화
@Builder    // 객체 생성을 간략화
@NoArgsConstructor  // 파라미터 없는 기본 생성자 자동 생성
@AllArgsConstructor // 멤버 변수 모두를 파라미터로 가지는 생성자 자도 ㅇ생성
public class Board {
    
    // 필드
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // 나중에 오라클로 바꿀 것이란 뜻
    private long bno;   // PK

    @Column(name = "title", length = 250)
    private String title;   // 글 제목

    @Column(name = "content", length = 1000)
    private String content; // 글 내용

    @CreatedDate
    @Column(name = "createDate", updatable = false)
    private LocalDateTime createDate;   // 글 생성일

    @LastModifiedDate
    @Column(name = "modifyDate")
    private LocalDateTime modifyDate;   // 2024.06.24 수정일 추가

    // @ColumnDefault("0")  // 생성될 때 초기값으로 0이 들어감
    // @Column(columnDefinition = "integer default 0")  
    private Integer hit;    // 24.06.26 조회수 추가

    // 관계
    // 사용자가 여러개의 게시글 작성 가능, 다대일 설정
    @ManyToOne
    private Member writer;

    // 중요, Relationship 1:n
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

    @ManyToOne
    private Category category;  // free, qna로 구분해서 글 생성 가능
}
