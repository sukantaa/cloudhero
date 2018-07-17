package com.javainuse.servicesImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainuse.repository.primary.PrimaryRepository;
import com.javainuse.repository.primary.Todo;
import com.javainuse.repository.primary.TodoNotFoundException;
import com.javainuse.repository.primary.TodoDTO;
import com.javainuse.services.TodoService;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * This service class saves {@link com.javaadvent.bootrest.todo.Todo} objects
 * to MongoDB database.
 * @author Sukanta Banerjee
 */
@Service
final class MongoDBTodoService implements TodoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBTodoService.class);

    private final PrimaryRepository repository;

    @Autowired
    MongoDBTodoService(PrimaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public TodoDTO create(TodoDTO todo) {
        LOGGER.info("Creating a new todo entry with information: {}", todo);

        Todo persisted = new Todo();
        persisted.setDescription(todo.getDescription());
        persisted.setTitle(todo.getTitle());
        persisted = repository.save(persisted);
        LOGGER.info("Created a new todo entry with information: {}", persisted);

        return convertToDTO(persisted);
    }

    @Override
    public TodoDTO delete(String id) {
        LOGGER.info("Deleting a todo entry with id: {}", id);

        Todo deleted = findTodoById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted todo entry with informtation: {}", deleted);

        return convertToDTO(deleted);
    }

    @Override
    public List<TodoDTO> findAll() {
        LOGGER.info("Finding all todo entries.");

        List<Todo> todoEntries = repository.findAll();

        LOGGER.info("Found {} todo entries", todoEntries.size());

        return convertToDTOs(todoEntries);
    }

    private List<TodoDTO> convertToDTOs(List<Todo> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }

    @Override
    public TodoDTO findById(String id) {
        LOGGER.info("Finding todo entry with id: {}", id);

        Todo found = findTodoById(id);

        LOGGER.info("Found todo entry: {}", found);

        return convertToDTO(found);
    }

    @Override
    public TodoDTO update(TodoDTO todo) {
        LOGGER.info("Updating todo entry with information: {}", todo);

        Todo updated = findTodoById(todo.getId());
        updated = repository.save(updated);

        LOGGER.info("Updated todo entry with information: {}", updated);

        return convertToDTO(updated);
    }

    private Todo findTodoById(String id) {
        Optional<Todo> result = repository.findOne(id);
        return result.orElseThrow(() -> new TodoNotFoundException(id));

    }

    private TodoDTO convertToDTO(Todo model) {
        TodoDTO dto = new TodoDTO();

        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setDescription(model.getDescription());

        return dto;
    }
}
