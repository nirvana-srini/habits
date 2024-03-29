package com.nirvana.habits.journal.todo.reflection;

import com.nirvana.habits.journal.todo.TodoRecord;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CrudRepositoryInterfaceImpl implements CurdRepositoryInterface {
    public CrudRepositoryInterfaceImpl() {
        log.info("Establishing the DB Connection");
        this.initialConfiguration();
        log.info("DB Connection successful");
    }

    private void initialConfiguration() {
        log.info("Initial Config Loaded");
    }

    @Override
    public TodoRecord findById(int i) {
        log.info("findById from Impl");
        return new TodoRecord(1l, "Proxy", "Proxy Pattern Example", false);
    }

    @Override
    public void findInternalCallCheck(int i) {
        log.info("findInternalCallCheck from Impl");
        log.info("calling this.findById from Impl with no transaction");
        this.findById(1);
    }

}
