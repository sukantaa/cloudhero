package com.javainuse.repository.secondary;

/**
 * This exception is thrown when the requested todo entry is not found.
 * @author Petri Kainulainen
 */
public class VodoNotFoundException extends RuntimeException {

    public VodoNotFoundException(String id) {
        super(String.format("No todo entry found with id: <%s>", id));
    }
}
