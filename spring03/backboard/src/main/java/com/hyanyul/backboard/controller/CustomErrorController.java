package com.hyanyul.backboard.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class CustomErrorController implements ErrorController{

    @RequestMapping("/error")
    public String error(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);  // 404, 500, 403 등(성공코드: 200)

        if(status != null){ // null이 아님 == 에러가 났다는 뜻
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()){
                return "error/404";
            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                return "error/500";     // 가장 중요, 개발자 문제이기 때문에 빠르게 디버깅해야함
            }

        }
        
        return "error/error";   // status 코드는 없는데 오류남

    }
    
}
