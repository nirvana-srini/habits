package com.nirvana.habits.journal.todo;

import java.util.Objects;

public record TodoRecord (Long id, String name, String description, boolean completed) {
    public TodoRecord {
        Objects.requireNonNull(description);
        Objects.requireNonNull(name);
    }

    public TodoRecord (Long id, String name, String description) {
        this(1l, name, description, false);
    }

}
