package com.javainuse.repository.secondary;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sukanta Banerjee
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "xx2_mongo")
public class Vodo {

    static final int MAX_LENGTH_DESCRIPTION = 500;
    static final int MAX_LENGTH_TITLE = 100;

    @Id
    private String id;

    private String description;

    private String title;


    

    @Override
    public String toString() {
        return String.format(
                "Todo[id=%s, description=%s, title=%s]",
                this.id,
                this.description,
                this.title
        );
    }
}
