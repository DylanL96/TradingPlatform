package com.example.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String symbol;
  private double price;
  private String companyName;

  public Stock(String symbol, double price, String companyName) {
      this.symbol = symbol;
      this.price = price;
      this.companyName = companyName;
  }

  public String getSymbol() {
      return symbol;
  }

  public double getPrice() {
      return price;
  }

  public String getCompanyName() {
      return companyName;
  }

}
