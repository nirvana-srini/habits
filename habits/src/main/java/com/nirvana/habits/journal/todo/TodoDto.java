package com.nirvana.habits.journal.todo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto implements Serializable {
    private Long id;
    @NotBlank(message = "The Todo name is required.")
    private String name;
    @NotBlank(message = "The Description value is required.")
    private String description;
    private boolean completed;
}
