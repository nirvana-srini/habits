package com.nirvana.habits.journal.todo.reflection;

public class MyRepositoryClient {
    static TodoRepositoryInterface repository = new CrudRepositoryProxy();

    public static void main(String[] args) {
        repository.findById(1);
        repository.findById(2);
        repository.findInternalCallCheck(1);
    }
}
