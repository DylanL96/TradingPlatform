package com.example.backend.Model;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "portfolio")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "portfolio_stock_quantities", joinColumns = @JoinColumn(name = "portfolio_id"))
    @MapKeyJoinColumn(name = "stock_id")
    @Column(name = "quantity")
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
