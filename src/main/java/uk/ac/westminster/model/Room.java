package uk.ac.westminster.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * Room model class.
 * Represents a physical room where sensors can be installed.
 */
public class Room {

    // Unique identifier
    private String id;

    // Human readable name
    private String name;

    // Optional description
    private String description;

    
    private List<String> sensorIds = new ArrayList<>();

    // Default constructor
    public Room() {
        this.id = UUID.randomUUID().toString();
    }

    // Constructor
    public Room(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

    // Getters & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // IMPORTANT pentru DELETE logic
    public List<String> getSensorIds() {
        return sensorIds;
    }

    public void setSensorIds(List<String> sensorIds) {
        this.sensorIds = sensorIds;
    }
}