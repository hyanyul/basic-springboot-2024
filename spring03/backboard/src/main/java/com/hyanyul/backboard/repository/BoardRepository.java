package com.hyanyul.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyanyul.backboard.entity.Board;

// 인터페이스만 있어도 CRUD 다 됨
@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
    
}