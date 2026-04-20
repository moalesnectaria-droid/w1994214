package uk.ac.westminster.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/*
 * This class provides a custom ObjectMapper configuration.
 * It enables support for Java 8 Date/Time API types such as LocalDateTime.
 * Without this, Jackson cannot serialize LocalDateTime into JSON.
 */
@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public ObjectMapperProvider() {
        mapper = new ObjectMapper();

        /*
         * Register JavaTimeModule to allow proper handling of
         * LocalDateTime and other java.time classes.
         */
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }
}