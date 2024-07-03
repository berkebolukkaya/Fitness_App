package com.project.cs310backendproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Exercise {
    @Id
    private String id;
    private String name;
    private List<String> muscle;
    private List<String> equipment;
    private String description;

    public Exercise(String name, List<String> muscle, List<String> equipment, String description) {
        this.name = name;
        this.muscle = muscle;
        this.equipment = equipment;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", muscle=" + muscle +
                ", equipment=" + equipment +
                ", description='" + description + '\'' +
                '}';
    }

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

    public List<String> getMuscle() {
        return muscle;
    }

    public void setMuscle(List<String> muscle) {
        this.muscle = muscle;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
