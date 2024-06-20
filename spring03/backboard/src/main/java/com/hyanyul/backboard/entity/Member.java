package com.hyanyul.backboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
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
}
