package com.example.backend.Repositories;

import com.example.backend.Models.Portfolio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
  
}
