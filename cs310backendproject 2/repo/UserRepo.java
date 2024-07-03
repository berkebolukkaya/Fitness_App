package com.project.cs310backendproject.repo;

import com.project.cs310backendproject.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    public List<User> findByUsername(String username);
}
