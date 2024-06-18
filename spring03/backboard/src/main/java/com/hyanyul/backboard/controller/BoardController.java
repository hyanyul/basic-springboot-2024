package com.hyanyul.backboard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyanyul.backboard.entity.Board;
import com.hyanyul.backboard.service.BoardService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/board")   // Restful URL은 /board로 시작
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //  @RequestMapping("/list", method=RequestMethod.GET)  // 아래와 동일 기능
    @GetMapping("/list")
    public String list(Model model) {   // Model -> controller에 있는 객체를 View로 보내주는 역할을 하는 객체
        List<Board> boardList = this.boardService.getList();
        model.addAttribute("boardList", boardList); // thymeleaf, mustache, jsp 등을 view로 보내는 기능
        return "board/list";    // templates/board/list.html 렌더링해서 리턴하라
    }     

    @GetMapping("/detail/{bno}")
    public String detail(Model model, @PathVariable("bno") Long bno) throws Exception {
        Board board = this.boardService.getBoard(bno);
        model.addAttribute("board", board);
        return "board/detail";
    }
    
}
