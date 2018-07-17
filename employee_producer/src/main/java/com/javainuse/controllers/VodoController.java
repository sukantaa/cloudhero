package com.javainuse.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.repository.secondary.VodoDTO;
import com.javainuse.repository.secondary.VodoNotFoundException;
import com.javainuse.services.VodoService;
import javax.validation.Valid;
import java.util.List;

/**
 * This controller provides the public API that is used to manage the information
 * of vodo entries.
 * @author Petri Kainulainen
 */

@RestController
@CrossOrigin
@RequestMapping("/api/vodo")
final class VodoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VodoController.class);

    
    private final VodoService service;

    @Autowired
    VodoController(VodoService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    VodoDTO create(@RequestBody @Valid VodoDTO vodoEntry) {
        LOGGER.info("Creating a new vodo entry with information: {}", vodoEntry);

        VodoDTO created = service.create(vodoEntry);
        LOGGER.info("Created a new vodo entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    VodoDTO delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting vodo entry with id: {}", id);

        VodoDTO deleted = service.delete(id);
        LOGGER.info("Deleted vodo entry with information: {}", deleted);

        return deleted;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<VodoDTO> findAll() {
        LOGGER.info("Finding all vodo entries");

        List<VodoDTO> todoEntries = service.findAll();
        LOGGER.info("Found {} vodo entries", todoEntries.size());

        return todoEntries;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    VodoDTO findById(@PathVariable("id") String id) {
        LOGGER.info("Finding vodo entry with id: {}", id);

        VodoDTO vodoEntry = service.findById(id);
        LOGGER.info("Found vodo entry with information: {}", vodoEntry);

        return vodoEntry;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    VodoDTO update(@RequestBody @Valid VodoDTO vodoEntry) {
        LOGGER.info("Updating vodo entry with information: {}", vodoEntry);

        VodoDTO updated = service.update(vodoEntry);
        LOGGER.info("Updated vodo entry with information: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleVodoNotFound(VodoNotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
}
