package com.project.cs310backendproject.model;

public class WorkoutPayload {
    private String userId;
    private String exerciseId;

    public WorkoutPayload(String userId, String exerciseId) {
        this.userId = userId;
        this.exerciseId = exerciseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }
}
