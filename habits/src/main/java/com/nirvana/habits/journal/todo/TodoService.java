package com.nirvana.habits.journal.todo;

import java.util.List;

public interface TodoService {

    List<TodoDto> findAll();

    List<TodoDto> findByNameStartingWith(String name);

    TodoDto save(TodoDto entity);

    TodoDto findById(Long id);

    TodoRecord fetchFirstTodo();

    void saveOrUpdate(TodoDto dto, Long id);

    void deleteAll();
}
