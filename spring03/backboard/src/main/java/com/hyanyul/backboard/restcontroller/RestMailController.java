package com.hyanyul.backboard.restcontroller;

import org.springframework.web.bind.annotation.RestController;

import com.hyanyul.backboard.service.MailService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
public class RestMailController {
    private final MailService mailService;

    @PostMapping("/test-email")
    @ResponseBody
    public ResponseEntity<HttpStatus> testEmail() {
        String to = "이메일";
        String subject = "전송 테스트 메일";
        String message = "테스트 메일 메시지입니다.";

        mailService.sendMail(to, subject, message);
        
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
    
}
