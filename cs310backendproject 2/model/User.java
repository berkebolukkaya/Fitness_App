package com.project.cs310backendproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {
    @Id
    private String id;
    private int age;
    private String username;
    private String password;
    @DBRef
    private List<Exercise> workoutPlan;

    public User(int age, String username, String password, List<Exercise> workoutPlan) {
        this.age = age;
        this.username = username;
        this.password = password;
        this.workoutPlan = workoutPlan;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", workoutPlan=" + workoutPlan +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Exercise> getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(List<Exercise> workoutPlan) {
        this.workoutPlan = workoutPlan;
    }
}
