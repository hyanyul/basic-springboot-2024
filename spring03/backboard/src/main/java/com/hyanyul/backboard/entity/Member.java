package com.hyanyul.backboard.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.hyanyul.backboard.security.MemberRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long mid;   // PK

    @Column(unique = true, length = 100)
    private String username;    // 사용자 이름

    @Column(unique = true, length = 150)
    private String email;   // 이메일

    private String password;    // 비밀번호

    @CreatedDate
    @Column(name = "regDate", updatable = false)
    private LocalDateTime regDate;   // 회원가입 날짜

    @Enumerated(EnumType.STRING)    // Enum타입이 STRING인 이유: "ROLE_ADMIN", "ROLE_USER"
    @Column(length = 15)
    private MemberRole role;
}
