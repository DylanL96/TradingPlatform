package com.example.backend.Repositories;

import com.example.backend.Models.Stock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
  
}
