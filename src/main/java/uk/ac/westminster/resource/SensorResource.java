package uk.ac.westminster.resource; // Defines package

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import uk.ac.westminster.model.Sensor;
import uk.ac.westminster.store.InMemoryStore;

import java.util.Collection;
import java.util.stream.Collectors;

/*
 * REST endpoints for managing sensors.
 */
@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    /*
     * GET /api/v1/sensors
     * Optional filtering by type (e.g. ?type=CO2)
     */
    @GET
    public Collection<Sensor> getSensors(@QueryParam("type") String type) {

        // Get all sensors from memory
        Collection<Sensor> sensors = InMemoryStore.getAllSensors();

        // If no filter is provided, return all sensors
        if (type == null) {
            return sensors;
        }

        // Filter sensors by type
        return sensors.stream()
                .filter(sensor -> sensor.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    /*
     * POST /api/v1/sensors
     * Creates a new sensor
     */
    @POST
    public Response createSensor(Sensor sensor) {

        // VALIDATION: check if room exists
        if (InMemoryStore.getRoom(sensor.getRoomId()) == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Room does not exist")
                    .build();
        }

        // Add sensor to memory
        InMemoryStore.addSensor(sensor);

        // Return created response
        return Response.status(Response.Status.CREATED)
                .entity(sensor)
                .build();
    }

    /*
     * GET /api/v1/sensors/{id}
     */
    @GET
    @Path("/{id}")
    public Response getSensor(@PathParam("id") String id) {

        Sensor sensor = InMemoryStore.getSensor(id);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Sensor not found")
                    .build();
        }

        return Response.ok(sensor).build();
    }

    /*
     * DELETE /api/v1/sensors/{id}
     */
    @DELETE
    @Path("/{id}")
    public Response deleteSensor(@PathParam("id") String id) {

        Sensor sensor = InMemoryStore.getSensor(id);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Sensor not found")
                    .build();
        }

        InMemoryStore.deleteSensor(id);

        return Response.ok("Sensor deleted").build();
    }
}
