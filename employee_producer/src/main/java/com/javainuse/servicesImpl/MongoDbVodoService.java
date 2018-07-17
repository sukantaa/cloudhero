package com.javainuse.servicesImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainuse.repository.secondary.Vodo;
import com.javainuse.repository.primary.TodoNotFoundException;
import com.javainuse.repository.secondary.SecondaryRepository;
import com.javainuse.repository.secondary.VodoDTO;
import com.javainuse.services.VodoService;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * This service class saves {@link com.javaadvent.bootrest.Vodo.Vodo} objects
 * to MongoDB database.
 * @author Sukanta Banerjee
 */
@Service
final class MongoDBVodoService implements VodoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBTodoService.class);

    private final SecondaryRepository repository;

    @Autowired
    MongoDBVodoService(SecondaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public VodoDTO create(VodoDTO Vodo) {
        LOGGER.info("Creating a new Vodo entry with information: {}", Vodo);

        Vodo persisted = new Vodo();
        persisted.setDescription(Vodo.getDescription());
        persisted.setTitle(Vodo.getTitle());
        persisted = repository.save(persisted);
        LOGGER.info("Created a new Vodo entry with information: {}", persisted);

        return convertToDTO(persisted);
    }

    @Override
    public VodoDTO delete(String id) {
        LOGGER.info("Deleting a Vodo entry with id: {}", id);

        Vodo deleted = findTodoById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted Vodo entry with informtation: {}", deleted);

        return convertToDTO(deleted);
    }

    @Override
    public List<VodoDTO> findAll() {
        LOGGER.info("Finding all Vodo entries.");

        List<Vodo> todoEntries = repository.findAll();

        LOGGER.info("Found {} Vodo entries", todoEntries.size());

        return convertToDTOs(todoEntries);
    }

    private List<VodoDTO> convertToDTOs(List<Vodo> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }

    @Override
    public VodoDTO findById(String id) {
        LOGGER.info("Finding Vodo entry with id: {}", id);

        Vodo found = findTodoById(id);

        LOGGER.info("Found Vodo entry: {}", found);

        return convertToDTO(found);
    }

    @Override
    public VodoDTO update(VodoDTO Vodo) {
        LOGGER.info("Updating Vodo entry with information: {}", Vodo);

        Vodo updated = findTodoById(Vodo.getId());
        updated = repository.save(updated);

        LOGGER.info("Updated Vodo entry with information: {}", updated);

        return convertToDTO(updated);
    }

    private Vodo findTodoById(String id) {
        Optional<Vodo> result = repository.findOne(id);
        return result.orElseThrow(() -> new TodoNotFoundException(id));

    }

    private VodoDTO convertToDTO(Vodo model) {
        VodoDTO dto = new VodoDTO();

        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setDescription(model.getDescription());

        return dto;
    }
}
