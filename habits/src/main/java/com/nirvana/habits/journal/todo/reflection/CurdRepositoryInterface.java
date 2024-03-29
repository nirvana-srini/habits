package com.nirvana.habits.journal.todo.reflection;

import com.nirvana.habits.journal.todo.TodoRecord;

public interface CurdRepositoryInterface {
    TodoRecord findById(int i);

    void findInternalCallCheck(int i);
}
