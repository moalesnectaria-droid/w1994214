package uk.ac.westminster.model; // Defines the package (must match the folder structure)

/*
 * Import UUID to generate a unique identifier for each sensor.
 * This follows the coursework requirement for unique IDs.
 */
import java.util.UUID;

/*
 * Sensor class represents a device in the Smart Campus system.
 * Example: temperature sensor, CO2 sensor, occupancy sensor.
 */
public class Sensor {

    /*
     * Unique identifier for the sensor.
     * Automatically generated using UUID.
     */
    private String id;

    /*
     * Type of the sensor.
     * Examples: "Temperature", "CO2", "Occupancy"
     */
    private String type;

    /*
     * Current status of the sensor.
     * Examples: "ACTIVE", "MAINTENANCE", "OFFLINE"
     */
    private String status;

    /*
     * The latest value recorded by the sensor.
     * Example: temperature = 23.5
     */
    private double currentValue;

    /*
     * ID of the room where this sensor is installed.
     * This creates a relationship between Sensor and Room.
     */
    private String roomId;

    /*
     * Default constructor (REQUIRED for JSON deserialization in JAX-RS).
     * Automatically generates a unique ID when a sensor is created.
     */
    public Sensor() {
        this.id = UUID.randomUUID().toString();
    }

    /*
     * Full constructor used when manually creating a sensor object.
     */
    public Sensor(String type, String status, double currentValue, String roomId) {
        this.id = UUID.randomUUID().toString(); // Generate unique ID
        this.type = type;                       // Set sensor type
        this.status = status;                   // Set sensor status
        this.currentValue = currentValue;       // Set current value
        this.roomId = roomId;                   // Link sensor to a room
    }

    /*
     * Getter for ID.
     * Allows reading the sensor ID.
     */
    public String getId() {
        return id;
    }

    /*
     * Getter for type.
     * Used later for filtering (e.g. ?type=CO2).
     */
    public String getType() {
        return type;
    }

    /*
     * Setter for type.
     * Used when receiving JSON data in POST requests.
     */
    public void setType(String type) {
        this.type = type;
    }

    /*
     * Getter for status.
     */
    public String getStatus() {
        return status;
    }

    /*
     * Setter for status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /*
     * Getter for current value.
     */
    public double getCurrentValue() {
        return currentValue;
    }

    /*
     * Setter for current value.
     */
    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    /*
     * Getter for room ID.
     * Used to check which room the sensor belongs to.
     */
    public String getRoomId() {
        return roomId;
    }

    /*
     * Setter for room ID.
     * Used when linking a sensor to a room.
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
