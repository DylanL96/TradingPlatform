package com.example.backend.Repositories;

import java.util.Optional;

import com.example.backend.Models.Portfolio;
import com.example.backend.Models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
  Optional<Portfolio> findByUser(User user);
}
