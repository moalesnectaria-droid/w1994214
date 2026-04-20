package uk.ac.westminster.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import uk.ac.westminster.model.Room;
import uk.ac.westminster.store.InMemoryStore;
import uk.ac.westminster.exception.RoomNotEmptyException;

import java.util.Collection;

/*
 * REST endpoints for managing rooms.
 */
@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    //  store instance (IMPORTANT)
    private InMemoryStore store = new InMemoryStore();

    /*
     * GET /api/v1/rooms
     */
    @GET
    public Collection<Room> getRooms() {
        return store.getAllRooms();
    }

    /*
     * GET /api/v1/rooms/{id}
     */
    @GET
    @Path("/{id}")
    public Response getRoom(@PathParam("id") String id) {

        Room room = store.getRoom(id);

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

        store.addRoom(room);

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

        Room room = store.getRoom(id);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Room not found")
                    .build();
        }

        //  throw exception (nu return manual)
        if (!room.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("Room still has sensors");
        }

        store.deleteRoom(id);

        return Response.ok("Room deleted").build();
    }
}