package uk.ac.westminster.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import uk.ac.westminster.model.Room;
import uk.ac.westminster.store.InMemoryStore;

import java.util.Collection;

/*
 * REST endpoints for managing rooms.
 */
@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    /*
     * GET /api/v1/rooms
     * Returns all rooms.
     */
    @GET
    public Collection<Room> getRooms() {
        return InMemoryStore.getAllRooms();
    }

    /*
     * GET /api/v1/rooms/{id}
     */
    @GET
    @Path("/{id}")
    public Response getRoom(@PathParam("id") String id) {

        Room room = InMemoryStore.getRoom(id);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Room not found")
                    .build();
        }

        return Response.ok(room).build();
    }

    /*
     * POST /api/v1/rooms
     */
    @POST
    public Response createRoom(Room room) {

        InMemoryStore.addRoom(room);

        return Response.status(Response.Status.CREATED)
                .entity(room)
                .build();
    }

    /*
     * DELETE /api/v1/rooms/{id}
     */
    @DELETE
@Path("/{id}")
public Response deleteRoom(@PathParam("id") String id) {

    Room room = InMemoryStore.getRoom(id);

    if (room == null) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Room not found")
                .build();
    }

    // check sensors
    boolean hasSensors = InMemoryStore.getAllSensors().stream()
            .anyMatch(sensor -> sensor.getRoomId().equals(id));

    if (hasSensors) {
        return Response.status(Response.Status.CONFLICT)
                .entity("Room has sensors and cannot be deleted")
                .build();
    }

    InMemoryStore.deleteRoom(id);

    return Response.ok("Room deleted").build();
    }
}