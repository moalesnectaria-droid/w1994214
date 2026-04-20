package uk.ac.westminster.store;

import uk.ac.westminster.model.Room;
import uk.ac.westminster.model.Sensor;
import uk.ac.westminster.model.SensorReading;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

/**
 * InMemoryStore simulates a database using in-memory data structures.
 * This approach is suitable for testing and small-scale applications
 * where persistence is not required.
 *
 * All data is stored in static HashMaps, representing tables.
 *
 * @author w1994214
 */
public class InMemoryStore {

    /**
     * Map storing all rooms.
     * Key: Room ID
     * Value: Room object
     */
    private static Map<String, Room> rooms = new HashMap<>();

    /**
     * Map storing all sensors.
     * Key: Sensor ID
     * Value: Sensor object
     */
    private static Map<String, Sensor> sensors = new HashMap<>();

    /**
     * Map storing sensor readings.
     * Key: Sensor ID
     * Value: List of readings associated with that sensor
     */
    private static Map<String, List<SensorReading>> readings = new HashMap<>();

    // ========================= ROOMS =========================

    /**
     * Retrieves all rooms.
     * @return collection of rooms
     */
    public static Collection<Room> getAllRooms() {
        return rooms.values();
    }

    /**
     * Retrieves a room by its ID.
     * @param id room identifier
     * @return Room object or null if not found
     */
    public static Room getRoom(String id) {
        return rooms.get(id);
    }

    /**
     * Adds a new room to the store.
     * @param room Room object
     */
    public static void addRoom(Room room) {
        rooms.put(room.getId(), room);
    }

    /**
     * Deletes a room by ID.
     * @param id room identifier
     */
    public static void deleteRoom(String id) {
        rooms.remove(id);
    }

    // ========================= SENSORS =========================

    /**
     * Retrieves all sensors.
     * @return collection of sensors
     */
    public static Collection<Sensor> getAllSensors() {
        return sensors.values();
    }

    /**
     * Retrieves a sensor by ID.
     * @param id sensor identifier
     * @return Sensor object or null
     */
    public static Sensor getSensor(String id) {
        return sensors.get(id);
    }

    /**
     * Adds a new sensor.
     * @param sensor Sensor object
     */
    public static void addSensor(Sensor sensor) {
        sensors.put(sensor.getId(), sensor);
    }

    /**
     * Deletes a sensor.
     * @param id sensor identifier
     */
    public static void deleteSensor(String id) {
        sensors.remove(id);
    }

    // ========================= SENSOR READINGS =========================

    /**
     * Retrieves all readings for a specific sensor.
     * If no readings exist, returns an empty list.
     *
     * @param sensorId sensor identifier
     * @return list of SensorReading objects
     */
    public static List<SensorReading> getReadings(String sensorId) {
        return readings.getOrDefault(sensorId, new ArrayList<>());
    }

    /**
     * Adds a reading to a specific sensor.
     * If the sensor has no readings yet, a new list is created.
     *
     * @param sensorId sensor identifier
     * @param reading SensorReading object
     */
    public static void addReading(String sensorId, SensorReading reading) {

        // Assign a unique ID to the reading
        String id = UUID.randomUUID().toString();
        reading.setId(id);

        // Ensure list exists for this sensor
        readings.putIfAbsent(sensorId, new ArrayList<>());

        // Add reading to the list
        readings.get(sensorId).add(reading);
    }

    /**
     * Retrieves all readings across all sensors.
     *
     * @return collection of all SensorReading objects
     */
    public static Collection<SensorReading> getAllReadings() {

        List<SensorReading> all = new ArrayList<>();

        // Combine all lists into one
        for (List<SensorReading> list : readings.values()) {
            all.addAll(list);
        }

        return all;
    }
}