package com.hyanyul.backboard.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyanyul.backboard.dto.Header;
import com.hyanyul.backboard.entity.Member;
import com.hyanyul.backboard.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
@Log4j2
public class RestMemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public Header<Member> login(@RequestParam Map<String, String> loginInfo){
        log.info(String.format("▶▶▶▶▶ React에서 넘어온 정보 : %s, %s", loginInfo.get("username"), loginInfo.get("password")));
        
        // 계정 정보 객체 생성(필요없음)
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = loginInfo.get("username");
        String password = loginInfo.get("password");

        try {
            Member member = this.memberService.getMemberByUsernameAndPassword(username, password);
    
            if(member != null){
                Header<Member> result = Header.OK(member);
                return result;
            } else {
                Header<Member> fail = Header.OK("Member not found");
                return fail;
            }

        } catch (Exception e) {
            log.debug(e);
            Header<Member> fail = Header.OK("Member not found");
            return fail;
        }

    }
}
