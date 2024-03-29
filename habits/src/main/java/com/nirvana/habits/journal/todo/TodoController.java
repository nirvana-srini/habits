package com.nirvana.habits.journal.todo;

import com.nirvana.habits.journal.MessageResponse;
import com.nirvana.habits.journal.context.TenantContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nirvana.habits.journal.context.NContext.TENANT_X_TENANT_ID_HEADER;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
@Slf4j
public class TodoController {


    @Autowired TenantContext context;
    @Autowired TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<List<TodoDto>> findAll(@RequestParam(value = "name", required = false) String name,
                                                 @RequestHeader(value = TENANT_X_TENANT_ID_HEADER, required = false) String tenantId) {
        if(!StringUtils.hasLength(tenantId)) {
            String tenantId1 = context.getTenant().getTenantId();
            log.info("TenantId = {} findAll method name = {}", tenantId1, name);
        }
        if(StringUtils.hasLength(name)) {
            log.info("TenantId = {} findAll method name = {}", tenantId, name);
            return ResponseEntity.ok(todoService.findByNameStartingWith(name));
        } else {
            log.info("findAll method");
            return ResponseEntity.ok(todoService.findAll());
        }

    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<TodoDto> findById(@PathVariable("id") Long id) {
        log.info("findById method id = {}", id);
        return ResponseEntity.ok(todoService.findById(id));
    }

    @GetMapping("/todos/top")
    public ResponseEntity<TodoRecord> fetchFirstTodo() {
        log.info("topTodo method");
        return ResponseEntity.ok(todoService.fetchFirstTodo());
    }

    @PostMapping("/todos")
    @Transactional
    public ResponseEntity<TodoDto> save(@Valid @RequestBody TodoDto dto) {
        log.info("save method todo = {}", dto);
        return new ResponseEntity<>(todoService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<MessageResponse> saveOrUpdate(@Valid @RequestBody TodoDto dto, @PathVariable Long id) {
        log.info("saveOrUpdate method id = {} and todo = {}", id, dto);
        todoService.saveOrUpdate(dto, id);
        return new ResponseEntity<>(new MessageResponse("Updated Successfully"), HttpStatus.CREATED);

    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable("id") Long id) {
        log.info("deleteById method id = {}", id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todos")
    public ResponseEntity<MessageResponse> deleteAll() {
        log.info("Delete All");
        todoService.deleteAll();
        return ResponseEntity.noContent().build();
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}

