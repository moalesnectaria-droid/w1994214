package uk.ac.westminster.model;

import java.time.LocalDateTime;
import java.util.UUID;

/*
 * Represents a reading from a sensor (e.g. CO2 value at a specific time).
 */
public class SensorReading {

    // Unique ID of the reading
    private String id;

    // Sensor value (e.g. CO2 level)
    private double value;

    // Timestamp of reading
    private LocalDateTime timestamp;

    // Sensor ID this reading belongs to
    private String sensorId;

    // Default constructor (required for JSON)
    public SensorReading() {
        this.id = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
    }
    public void setId(String id) {
    this.id = id;
    }

    public String getId() { return id; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getSensorId() { return sensorId; }
    public void setSensorId(String sensorId) { this.sensorId = sensorId; }
}