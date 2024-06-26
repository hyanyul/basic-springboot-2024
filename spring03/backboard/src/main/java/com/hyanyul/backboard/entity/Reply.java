package com.hyanyul.backboard.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long rno;

    @Column(name = "content", length = 1000)
    private String content;

    @CreatedDate
    @Column(name = "createDate", updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "modifyDate")
    private LocalDateTime modifyDate;   // 2024.06.24 수정일 추가
    
    // 중요, ERD로 DB 설계하지 않고 엔티티 클래스로 관계 형성 시 반드시 사용해야 함
    // Relationship n:1
    @ManyToOne
    private Board board;

    // 사용자가 여러개의 게시글 작성 가능, 다대일 설정
    @ManyToOne
    private Member writer;
}
