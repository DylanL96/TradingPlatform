package com.example.backend.Service;

import com.example.backend.Models.Stock;
import com.example.backend.Models.User;

public interface UserPortfolioService {
  void addToPortfolio(User user, Stock stock);
}
