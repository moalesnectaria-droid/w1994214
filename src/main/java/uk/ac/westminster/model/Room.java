
package uk.ac.westminster.model;
import java.util.UUID;

/*
 * Room model class.
 * Represents a physical room where sensors can be installed.
 */
/**
 *
 * @author w1994214
 */
public class Room {
   /*
     * Unique identifier of the room.
     */
    private String id;

    /*
     * Human readable room name (e.g. "Lab A", "Room 101").
     */
    private String name;

    /*
     * Optional room description.
     */
    private String description;

    /*
     * Default constructor required for JSON serialization.
     */
    public Room() {
        this.id = UUID.randomUUID().toString();
    }

    /*
     * Constructor used when creating a new room.
     */
    public Room(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

    /*
     * Getter for room id.
     */
    public String getId() {
        return id;
    }

    /*
     * Setter for room id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /*
     * Getter for room name.
     */
    public String getName() {
        return name;
    }

    /*
     * Setter for room name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Getter for room description.
     */
    public String getDescription() {
        return description;
    }

    /*
     * Setter for room description.
     */
    public void setDescription(String description) {
        this.description = description;
    } 
}
