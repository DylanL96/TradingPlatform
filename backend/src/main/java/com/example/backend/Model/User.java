package com.example.backend.Model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String username;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    private double cashBalance;

    public User() {
        // Default constructor required by JPA
    }

    public User(String username, String password, double initialCashBalance) {
        this.username = username;
        this.password = password;
        this.portfolio = new Portfolio();
        this.cashBalance = initialCashBalance;
    }

    public void tradeStock(Stock stock, int quantity) {
        // Check if the user has sufficient funds
        double totalPrice = stock.getPrice() * quantity;
        if (totalPrice <= cashBalance) {
            // Update user's portfolio and deduct the purchase cost from cash balance
            portfolio.addStock(stock, quantity);
            cashBalance -= totalPrice;
        } else {
            // Handle insufficient funds scenario
            System.out.println("Insufficient funds to complete the trade.");
        }
    }

    public void buyStock(Stock stock, int quantity) {
        double totalPrice = stock.getPrice() * quantity;
        if (totalPrice <= cashBalance) {
            portfolio.addStock(stock, quantity);
            cashBalance -= totalPrice;
        } else {
            System.out.println("Insufficient funds to buy the stock.");
        }
    }

    public void sellStock(Stock stock, int quantity) {
        int currentQuantity = portfolio.getStockQuantity(stock);
        if (currentQuantity >= quantity) {
            portfolio.removeStock(stock, quantity);
            double totalPrice = stock.getPrice() * quantity;
            cashBalance += totalPrice;
        } else {
            System.out.println("Insufficient quantity to sell the stock.");
        }
    }

    // Other methods and getters/setters
}
