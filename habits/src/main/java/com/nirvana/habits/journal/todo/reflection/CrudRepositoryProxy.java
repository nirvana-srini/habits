package com.nirvana.habits.journal.todo.reflection;

import com.nirvana.habits.journal.todo.TodoRecord;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CrudRepositoryProxy implements TodoRepositoryInterface {
    Singleton singleton = Singleton.INSTANCE;
    CrudRepositoryProxy(){
        //singleton = Singleton.INSTANCE.getValue();
    }

    enum Singleton {
        INSTANCE;

        CurdRepositoryInterface impl;
        public CurdRepositoryInterface getValue() {
            if(impl == null) {
                impl = new CrudRepositoryInterfaceImpl();
            }
            return impl;
        }

    }

    @Override
    public TodoRecord findById(int i) {
        log.info("findById Calling from proxy");
        log.info("findById Transaction Id={} Initiated", i);
        var a = singleton.getValue().findById(1);
        log.info("findById Transaction Id={} Completed", i);
        return a;
    }

    @Override
    public void findInternalCallCheck(int i) {
        log.info("findInternalCallCheck from proxy");
        log.info("findInternalCallCheck Transaction Id={} Initiated", i);
        singleton.getValue().findInternalCallCheck(1);
        log.info("findInternalCallCheck Transaction Id={} Completed", i);
    }
}