package com.example.backend.Models;

import java.util.HashMap;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ID;
  private String name;
  private String email;
  private String password;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "portfolio_id")
  private Portfolio portfolio;

  public void addToPortfolio(Stock stock) {
    if (portfolio == null) {
        portfolio = new Portfolio();
        portfolio.setUser(this);
        portfolio.setStocks(new HashMap<>());
    }
    portfolio.getStocks().put(stock.getSymbol(), stock.getQuantity());
    stock.setPortfolio(portfolio);
}
}
