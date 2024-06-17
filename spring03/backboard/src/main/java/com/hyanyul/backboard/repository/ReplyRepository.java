package com.hyanyul.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyanyul.backboard.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long>{
    
}
