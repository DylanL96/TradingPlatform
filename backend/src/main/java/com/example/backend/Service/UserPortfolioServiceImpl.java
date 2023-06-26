package com.example.backend.Service;

import java.util.Map;
import java.util.Optional;

import com.example.backend.Models.Portfolio;
import com.example.backend.Models.Stock;
import com.example.backend.Models.User;
import com.example.backend.Repositories.PortfolioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPortfolioServiceImpl implements UserPortfolioService {

  private PortfolioRepository portfolioRepository;

  @Autowired
  public UserPortfolioServiceImpl(PortfolioRepository portfolioRepository) {
      this.portfolioRepository = portfolioRepository;
  }

  @Override
  public void buyStock(User user, Stock stock, int quantity) {
      Portfolio portfolio = user.getPortfolio();
      Map<String, Integer> stockMap = portfolio.getStocks();
  
      // Check if the stock is already present in the portfolio
      if (stockMap.containsKey(stock.getSymbol())) {
          // Stock is already in the portfolio, update the quantity
          int currentQuantity = stockMap.get(stock.getSymbol());
          int newQuantity = currentQuantity + quantity;
          stockMap.put(stock.getSymbol(), newQuantity);
      } else {
          // Stock is not in the portfolio, add a new entry
          stockMap.put(stock.getSymbol(), quantity);
      }
  
      // Save the updated portfolio
      portfolioRepository.save(portfolio);
  }
  
  
}
