package com.project.cs310backendproject.model;

import java.util.List;

public class ExercisePayload {
    private String name;
    private List<String> muscle;
    private List<String> equipment;

    public ExercisePayload(String name, List<String> muscle, List<String> equipment) {
        this.name = name;
        this.muscle = muscle;
        this.equipment = equipment;
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
}
