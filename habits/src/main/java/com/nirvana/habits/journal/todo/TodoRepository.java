package com.nirvana.habits.journal.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    //generate derived query methods using keywords, like FirstBy, OrderBy, and Distinct acting as the subject and And, Or, and OrderBy as the predicate
    List<Todo> findByNameStartingWithIgnoreCase(String name);

    @Query("from Todo t order by t.id asc limit 1")
    Todo fetchFirstTodo();

}
