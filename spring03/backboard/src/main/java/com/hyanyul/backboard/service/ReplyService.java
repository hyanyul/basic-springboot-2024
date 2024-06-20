package com.hyanyul.backboard.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.hyanyul.backboard.entity.Board;
import com.hyanyul.backboard.entity.Reply;
import com.hyanyul.backboard.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyService {
    private final ReplyRepository replyRepository;

    public void setReply(Board board, String content){
        // 빌더를 사용한 방식
        Reply reply = Reply.builder().content(content).createDate(LocalDateTime.now()).board(board).build();
        log.info("댓글 객체 생성");
        this.replyRepository.save(reply);
        log.info("댓글 객체 저장 성공");
    }
}