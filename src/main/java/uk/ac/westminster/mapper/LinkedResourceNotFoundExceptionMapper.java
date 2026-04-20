
/**
 *
 * @author w1994214
 */
package uk.ac.westminster.mapper;

import uk.ac.westminster.exception.LinkedResourceNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LinkedResourceNotFoundExceptionMapper implements ExceptionMapper<LinkedResourceNotFoundException> {

    @Override
    public Response toResponse(LinkedResourceNotFoundException ex) {
        return Response.status(422) // Unprocessable Entity
                .entity("{\"error\": \"Linked resource not found\"}")
                .build();
    }
}