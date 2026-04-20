package uk.ac.westminster.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import uk.ac.westminster.model.SensorReading;
import uk.ac.westminster.store.InMemoryStore;

import java.util.List;

/*
 * REST endpoints for sensor readings
 */
@Path("/sensors/{sensorId}/readings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    /*
     * GET /api/v1/sensors/{sensorId}/readings
     * Returns all readings for a sensor
     */
    @GET
    public Response getReadings(@PathParam("sensorId") String sensorId) {

        // 🔍 Check if sensor exists
        if (InMemoryStore.getSensor(sensorId) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Sensor not found")
                    .build();
        }

        // Get readings
        List<SensorReading> readings = InMemoryStore.getReadings(sensorId);

        return Response.ok(readings).build();
    }

    /*
     * POST /api/v1/sensors/{sensorId}/readings
     * Add a new reading to a sensor
     */
    @POST
    public Response addReading(@PathParam("sensorId") String sensorId,
                              SensorReading reading) {

        // 🔍 Check if sensor exists
        if (InMemoryStore.getSensor(sensorId) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Sensor not found")
                    .build();
        }

        // Link reading to sensor
        reading.setSensorId(sensorId);

        // Save reading
        InMemoryStore.addReading(sensorId, reading);

        return Response.status(Response.Status.CREATED)
                .entity(reading)
                .build();
    }
}