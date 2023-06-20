package com.example.backend.Repository;

import java.util.Optional;

import com.example.backend.Entity.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Integer>{
  Optional<UserInfo> findByName(String username);
}
