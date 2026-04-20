package uk.ac.westminster.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * @author w1994214
 */


/*
 * LoggingFilter
 * 
 * This filter logs every incoming request and outgoing response.
 * It implements both ContainerRequestFilter and ContainerResponseFilter
 * as required by the coursework specification.
 */
@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    // Logger instance (standard Java logging)
    private static final Logger LOGGER = Logger.getLogger(LoggingFilter.class.getName());

    /*
     * This method is called BEFORE the request reaches the resource.
     * It logs the HTTP method and requested URI.
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String method = requestContext.getMethod();
        String uri = requestContext.getUriInfo().getRequestUri().toString();

        LOGGER.info("Incoming Request -> Method: " + method + " | URI: " + uri);
    }

    /*
     * This method is called AFTER the response is generated.
     * It logs the HTTP response status code.
     */
    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        int status = responseContext.getStatus();

        LOGGER.info("Outgoing Response -> Status: " + status);
    }
}
