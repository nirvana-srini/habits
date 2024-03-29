package com.nirvana.habits.journal.todo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDate;

//
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Todo extends AbstractAggregateRoot<Todo> {

    @Id
    @SequenceGenerator(name = "todoSeq", sequenceName = "todo_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "todoSeq")
    private Long id;
    private String name;
    private String description;
    private boolean completed;
    @CreationTimestamp
    private LocalDate createDate;


    public void afterSave() {
        TodoCreatedEvent event = new TodoCreatedEvent();
        event.setId(this.id);
        registerEvent(event);
    }
}
