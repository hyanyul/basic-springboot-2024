package com.hyanyul.backboard.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hyanyul.backboard.entity.Member;
import com.hyanyul.backboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;

    public Member setMember(String username, String email, String password){
        Member member = Member.builder().username(username).email(email).build();

        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        member.setPassword(pwEncoder.encode(password)); // 암호화한 값을 DB에 저장 준비
        this.memberRepository.save(member);

        return member;
    }
}
