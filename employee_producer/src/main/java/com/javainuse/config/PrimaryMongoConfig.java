package com.javainuse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static com.javainuse.config.PrimaryMongoConfig.MONGO_TEMPLATE;

/**
 * @author Sukanta Banerjee
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.javainuse.repository.primary",
        mongoTemplateRef = MONGO_TEMPLATE)
public class PrimaryMongoConfig {

    protected static final String MONGO_TEMPLATE = "primaryMongoTemplate";
}