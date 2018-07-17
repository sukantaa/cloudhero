package com.javainuse.repository.secondary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javainuse.repository.secondary.Vodo;

/**
 * @author Sukanta Banerjee
 */
public interface SecondaryRepository extends Repository<Vodo, String> {
	
	/**
     * Deletes a Vodo entry from the database.
     * @param deleted   The deleted Vodo entry.
     */
    void delete(Vodo deleted);

    /**
     * Finds all Vodo entries from the database.
     * @return  The information of all Vodo entries that are found from the database.
     */
    List<Vodo> findAll();

    /**
     * Finds the information of a single Vodo entry.
     * @param id    The id of the requested Vodo entry.
     * @return      The information of the found Vodo entry. If no Vodo entry
     *              is found, this method returns an empty {@link java.util.Optional} object.
     */
    Optional<Vodo> findOne(String id);

    /**
     * Saves a new Vodo entry to the database.
     * @param persisted The information of the saved Vodo entry.
     * @return      The information of the saved Vodo entry.
     */
    Vodo save(Vodo persisted);
}