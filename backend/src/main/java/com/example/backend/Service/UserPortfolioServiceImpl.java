package com.example.backend.Service;

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
      // Check if the stock is already present in the portfolio
      Optional<Stock> existingStock = portfolio.getStocks().stream()
              .filter(s -> s.getSymbol().equals(stock.getSymbol()))
              .findFirst();
  
      if (existingStock.isPresent()) {
          // Stock is already in the portfolio, update the quantity
          Stock stockToUpdate = existingStock.get();
          int newQuantity = stockToUpdate.getQuantity() + quantity;
          stockToUpdate.setQuantity(newQuantity);
      } else {
          // Stock is not in the portfolio, add a new entry
          stock.setQuantity(quantity);
          portfolio.getStocks().add(stock);
      }
  
      // Save the updated portfolio
      portfolioRepository.save(portfolio);
  }
  
}
