package com.hyanyul.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyanyul.backboard.entity.Reset;
import java.util.Optional;


public interface ResetRepository extends JpaRepository<Reset, Integer> {
    Optional<Reset> findByUuid(String uuid);    // UUID로 테이블 검색
}
