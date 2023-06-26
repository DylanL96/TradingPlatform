package com.example.backend.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Portfolio portfolio;

  public void addToPortfolio(Stock stock) {
    if (portfolio == null) {
        portfolio = new Portfolio();
        portfolio.setUser(this);
    }
    portfolio.getStocks().add(stock);
    stock.setPortfolio(portfolio);
}
}
