package com.example.backend.Service;

import com.example.backend.Models.Portfolio;
import com.example.backend.Models.Stock;
import com.example.backend.Models.User;
import com.example.backend.Repositories.PortfolioRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class UserPortfolioServiceImpl implements UserPortfolioService {

  private PortfolioRepository portfolioRepository;

  @Autowired
  public UserPortfolioServiceImpl(PortfolioRepository portfolioRepository) {
      this.portfolioRepository = portfolioRepository;
  }

  @Override
  public void addToPortfolio(User user, Stock stock) {
      // Implement the logic to add the stock to the user's portfolio
      user.addToPortfolio(stock);
  }
  
}
