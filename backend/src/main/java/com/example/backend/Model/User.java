package com.example.backend.Model;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long userID;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    private double cashBalance;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
