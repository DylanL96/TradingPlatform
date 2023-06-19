package com.example.backend.Repository;

import java.util.Optional;

import com.example.backend.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
  Optional<User> findByEmail(String email);
}
