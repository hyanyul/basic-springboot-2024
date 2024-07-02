package com.hyanyul.backboard.dto;

import java.time.LocalDateTime;

import com.hyanyul.backboard.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
    private long rno;
    
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    private Board board;

    private String writer;

}
