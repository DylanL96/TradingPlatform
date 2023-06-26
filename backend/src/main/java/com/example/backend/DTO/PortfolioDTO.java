package com.example.backend.DTO;

import java.util.Map;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioDTO {
  private Long portfolioID;
  private Long userID;
  private Map<String, Integer> stocks;

  public void setStocks(Map<String, Integer> stocks) {
    this.stocks = stocks;
  }

}
