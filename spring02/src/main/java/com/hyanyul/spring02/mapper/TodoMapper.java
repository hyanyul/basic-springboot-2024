package com.hyanyul.spring02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyanyul.spring02.domain.Todo;

@Mapper
public interface TodoMapper {
    
    List<Todo> selectTodosAll();

    Todo selecTodo(int tno);
}
