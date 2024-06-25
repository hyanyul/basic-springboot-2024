package com.hyanyul.backboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hyanyul.backboard.common.NotFoundException;
import com.hyanyul.backboard.entity.Category;
import com.hyanyul.backboard.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;    // bean으로 생성

    // 카테고리 생성 메서드
    public Category setCategory(String title){
        Category cate = new Category();
        cate.setTitle(title);
        cate.setCreateDate(LocalDateTime.now());

        Category category = this.categoryRepository.save(cate);

        return category;
    }  
    
    // free, qna, notice
    public Category getCategory(String title){
        Optional<Category> cate = this.categoryRepository.findByTitle(title);

        if(cate.isEmpty()){ // 없는 카테고리이면
            cate = Optional.ofNullable(setCategory(title)); // 테이블에 해당 카테고리 생성
        }

        if(cate.isPresent()){
            return cate.get();  // category 리턴
        } else{
            throw new NotFoundException("Category not found");  // 발생 가능성 거의 없음
        }
    }
}
