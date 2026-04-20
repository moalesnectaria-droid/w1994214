/**
 *
 * @author w1994214
 */
package uk.ac.westminster.mapper;

import uk.ac.westminster.exception.SensorUnavailableException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SensorUnavailableExceptionMapper implements ExceptionMapper<SensorUnavailableException> {

    @Override
    public Response toResponse(SensorUnavailableException ex) {
        return Response.status(Response.Status.FORBIDDEN) // 403
                .entity("{\"error\": \"Sensor is in maintenance mode\"}")
                .build();
    }
}