package com.example.backend.Repositories;

import com.example.backend.Models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findFirstByEmail(String email);
  @Query("SELECT u.id FROM User u WHERE u.email = :email")
  Long findIdByEmail(@Param("email") String email);
}