package com.javainuse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static com.javainuse.config.SecondaryMongoConfig.MONGO_TEMPLATE;

/**
 * @author Sukanta Banerje
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.javainuse.repository.secondary",
        mongoTemplateRef = MONGO_TEMPLATE)
public class SecondaryMongoConfig {

    protected static final String MONGO_TEMPLATE = "secondaryMongoTemplate";
}