package com.example.backend.Model;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "portfolio")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private Map<Stock, Integer> stocks;

    public Portfolio() {
        this.stocks = new HashMap<>();
    }

    public void addStock(Stock stock, int quantity) {
        stocks.put(stock, stocks.getOrDefault(stock, 0) + quantity);
    }

    public void removeStock(Stock stock, int quantity) {
        int currentQuantity = stocks.getOrDefault(stock, 0);
        if (currentQuantity > quantity) {
            stocks.put(stock, currentQuantity - quantity);
        } else {
            stocks.remove(stock);
        }
    }

    public int getStockQuantity(Stock stock) {
        return stocks.getOrDefault(stock, 0);
    }

}
