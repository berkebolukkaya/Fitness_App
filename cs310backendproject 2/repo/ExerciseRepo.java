package com.project.cs310backendproject.repo;

import com.project.cs310backendproject.model.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExerciseRepo extends MongoRepository<Exercise, String> {
    public List<Exercise> findByNameContainsIgnoreCase(String name);
    public List<Exercise> findByMuscleContainsIgnoreCase(String muscle);
    public List<Exercise> findByEquipmentContainsIgnoreCase(String equipment);
    public List<Exercise> findByMuscleContainsIgnoreCaseAndEquipmentContainsIgnoreCase
            (String muscle, String equipment);
}
