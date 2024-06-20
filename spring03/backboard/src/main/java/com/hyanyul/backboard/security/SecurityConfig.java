package com.hyanyul.backboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 스프링 시큐리티 핵심 파일
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http://localhost:8080/** 로그인하지 않아도 접근할 수 있는 권한 줌
        http
            .authorizeHttpRequests((atr) -> atr.requestMatchers(new AntPathRequestMatcher("/**"))
                    .permitAll())
            // CSRF 위변조 공격을 막는 부분 해제, 특정 URL은 csrf공격 리스트에서 제거
            .csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
            // h2-console 페이지가 frameset, frame으로 구성 CORS와 유사한 옵션추가
            .headers((headers) -> headers
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                     XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN // ignoringRequestMatchers 영역에 있는
                                                                                       // 프레임이니 해제해달라는 뜻
            )));
        return http.build();

    }
}
