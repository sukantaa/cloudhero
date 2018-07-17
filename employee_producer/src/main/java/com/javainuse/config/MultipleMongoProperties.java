package com.javainuse.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author Sukanta Banerjee
 */
@Data
@ConfigurationProperties(prefix = "mongodb")
public class MultipleMongoProperties {

    private MongoProperties primary = new MongoProperties();
    private MongoProperties secondary = new MongoProperties();
    
}