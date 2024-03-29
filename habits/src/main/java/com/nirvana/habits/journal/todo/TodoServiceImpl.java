package com.nirvana.habits.journal.todo;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Slf4j
@Transactional
public class TodoServiceImpl implements TodoService {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    private TodoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public TodoServiceImpl(TodoRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Cacheable(cacheNames = "todos", key = "'todos'")
    public List<TodoDto> findAll() {
        log.info("FindAll from DB method");
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = "todos", key = "'todos_'.concat(#name)")
    public List<TodoDto> findByNameStartingWith(String name) {
        log.info("FindAll from DB method name = {}", name);
        return repository.findByNameStartingWithIgnoreCase(name).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public TodoDto save(TodoDto entity) {
        evictAllCaches();
        return convertToDTO(repository.save((convertToEntity(entity))));
    }

    @Override
    public TodoDto findById(Long id) {
        return convertToDTO(this.findEntityById(id));
    }

    @Override
    public TodoRecord fetchFirstTodo() {
        Todo todo = repository.fetchFirstTodo();
        return new TodoRecord(todo.getId(), todo.getName(), todo.getDescription(), todo.isCompleted());
    }

    @Override
    public void saveOrUpdate(TodoDto dto, Long id) {
        Todo entity = this.findEntityById(id);
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setCompleted(dto.isCompleted());
        evictAllCaches();
        repository.save(entity);
    }

    @Override
    public void deleteAll() {
        evictAllCaches();
        repository.deleteAll();
    }

    private TodoDto convertToDTO(Todo entity) {
        return modelMapper.map(entity, TodoDto.class);
    }

    private Todo convertToEntity(TodoDto dto) {
        return modelMapper.map(dto, Todo.class);
    }

    private Todo findEntityById(Long id) {
        return repository.findById(id).orElseThrow( () -> new RuntimeException("Record Not Found Error"));
    }

    @Cacheable(cacheNames = "myControlledCache")
    public String getFromCache() {
        return null;
    }

    @CachePut(cacheNames = "myControlledCache")
    public String populateCache() {
        return "this is it again!";
    }

    public void evictAllCaches() {
        cacheManager.getCacheNames()
                .forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
    }
}
