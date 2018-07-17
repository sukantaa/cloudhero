package com.javainuse.services;

import java.util.List;

import com.javainuse.repository.secondary.VodoDTO;

/**
 * This interface declares the methods that provides CRUD operations for
 * {@link com.javaadvent.bootrest.vodo.vodo} objects.
 * @author Sukanta Banerjee
 */
public interface VodoService {

    /**
     * Creates a new vodo entry.
     * @param vodo  The information of the created vodo entry.
     * @return      The information of the created vodo entry.
     */
    VodoDTO create(VodoDTO vodo);

    /**
     * Deletes a vodo entry.
     * @param id    The id of the deleted vodo entry.
     * @return      THe information of the deleted vodo entry.
     * @throws com.javaadvent.bootrest.vodo.vodoNotFoundException if no vodo entry is found.
     */
    VodoDTO delete(String id);

    /**
     * Finds all vodo entries.
     * @return      The information of all vodo entries.
     */
    List<VodoDTO> findAll();

    /**
     * Finds a single vodo entry.
     * @param id    The id of the requested vodo entry.
     * @return      The information of the requested vodo entry.
     * @throws com.javaadvent.bootrest.vodo.vodoNotFoundException if no vodo entry is found.
     */
    VodoDTO findById(String id);

    /**
     * Updates the information of a vodo entry.
     * @param vodo  The information of the updated vodo entry.
     * @return      The information of the updated vodo entry.
     * @throws com.javaadvent.bootrest.vodo.vodoNotFoundException if no vodo entry is found.
     */
    VodoDTO update(VodoDTO vodo);
}
