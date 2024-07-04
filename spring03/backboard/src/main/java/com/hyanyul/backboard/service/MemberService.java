package com.hyanyul.backboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hyanyul.backboard.common.NotFoundException;
import com.hyanyul.backboard.entity.Member;
import com.hyanyul.backboard.repository.MemberRepository;
import com.hyanyul.backboard.security.MemberRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    // 새로운 사용자 생성
    public Member setMember(String username, String email, String password){
        Member member = Member.builder().username(username).email(email).regDate(LocalDateTime.now()).build();
        
        // BcryptPasswordEncoder 매번 새롭게 객체 생성 -> Bean 등록이 더 좋음(유지보수)
        // BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(password)); // 암호화한 값을 DB에 저장 준비
        member.setRegDate(LocalDateTime.now()); // 처리되는 일이 많아서 1~2초 시간이 걸리는 경우 대비
        member.setRole(MemberRole.USER);    // 일반 사용자 권한 줌
        this.memberRepository.save(member);

        return member;
    }
    
    // 기존 사용자 비밀번호 초기화
    public void setMember(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));   // BCrypt 암호화
        this.memberRepository.save(member); // 업데이트
    }
    
    public Member getMember(String username){
        Optional<Member> member = this.memberRepository.findByUsername(username);
        if(member.isPresent()){
            return member.get();
        } else{
            throw new NotFoundException("Member not found");
        }
    }

    // 24.06.28 이메일로 사용자 검색 메서드
    public Member getMemberByEmail(String email){
        Optional<Member> member = this.memberRepository.findByEmail(email);
        if(member.isPresent()){
            return member.get();
        } else{
            throw new NotFoundException("Member not found");
        }
    }

    // 24.07.04 React에서 넘어온 정보로 로그인 확인
    public Member getMemberByUsernameAndPassword(String username, String password) {
        Optional<Member> _member = this.memberRepository.findByUsername(username);
        Member realMember;

        if(_member.isPresent()){
            realMember = _member.get(); // 같은 이름의 사용자 정보가 다 넘어옴(암호화된 비밀번호까지)

            // plain text와 암호화된 값이 같은 값을 가지고 있는지 체크
            boolean isMatched = passwordEncoder.matches(password, realMember.getPassword());

            if(isMatched){
                return realMember;
            } else{
                throw new NotFoundException("Member not found");
            }
        } else{
            throw new NotFoundException("Member not found");
        }
    }
}