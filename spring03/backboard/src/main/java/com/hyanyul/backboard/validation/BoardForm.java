package com.hyanyul.backboard.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardForm {
    // entity에 추가 가능, 일이 복잡해짐 -> validation 따로 만드는 게 좋음

    @Size(max = 250)
    @NotBlank(message = "제목을 입력하세요.")
    private String title;
    
    @NotBlank(message = "내용은 필수입니다.")
    private String content;
}