
/**
 *
 * @author w1994214
 */

package uk.ac.westminster.mapper;

import uk.ac.westminster.exception.RoomNotEmptyException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {

    @Override
    public Response toResponse(RoomNotEmptyException ex) {
        return Response.status(Response.Status.CONFLICT) // 409
                .entity("{\"error\": \"Room still has sensors assigned\"}")
                .build();
    }
}