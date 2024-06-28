package com.hyanyul.backboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyanyul.backboard.entity.Member;
import com.hyanyul.backboard.service.MailService;
import com.hyanyul.backboard.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping("/mail")
@RequiredArgsConstructor
@Controller
@Log4j2
public class MailController {

    private final MemberService memberService;
    private final MailService mailService;

    @PostMapping("/reset-mail")
    public String resetMail(Model model, @RequestParam("email") String email) {
        log.info(String.format("▶▶▶▶▶ reset.html에서 넘어온 이메일: %s", email));

        // DB에서 메일 주소가 있는지 확인
        // 있으면 초기화 메일 보냄
        // 없으면 에러

        try {
            Member member = this.memberService.getMemberByEmail(email);

            // 메일 전송
            boolean result = this.mailService.sendResetPasswordEmail(member.getEmail());

            if(result){
                log.info("▶▶▶▶▶ 초기화 메일 전송 완료");
                model.addAttribute("result", "초기화 메일 전송 성공");
            }
            else{
                model.addAttribute("result", "초기화 메일 전송 실패. 관리자에게 문의하세요.");
            }
        } catch (Exception e) {
            model.addAttribute("result", "초기화 메일 전송 실패. 사용자가 없습니다.");
        }

        return "member/reset_result";   //member/reset_result.html 파일 생성
    }
    
}
