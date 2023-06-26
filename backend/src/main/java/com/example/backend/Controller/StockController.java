package com.example.backend.Controller;

import java.util.HashMap;
import java.util.Map;

import com.example.backend.DTO.PortfolioDTO;
import com.example.backend.DTO.StockRequestDTO;
import com.example.backend.Models.Portfolio;
import com.example.backend.Models.Stock;
import com.example.backend.Models.User;
import com.example.backend.Repositories.PortfolioRepository;
import com.example.backend.Repositories.StockRepository;
import com.example.backend.Repositories.UserRepository;
import com.example.backend.Service.UserPortfolioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;
    
    @Autowired
    private UserPortfolioService userPortfolioService;
    
    @PostMapping("/{userId}/buy/{stockId}")
    public ResponseEntity<String> buyStock(
            @PathVariable("userId") Long userId,
            @RequestBody StockRequestDTO requestDTO) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundException());
            Stock stock = stockRepository.findById(requestDTO.getStockId())
                    .orElseThrow(() -> new NotFoundException());
    
            // Check if the requested quantity is available
            if (stock.getQuantity() < requestDTO.getQuantity()) {
                return ResponseEntity.badRequest().body("Insufficient stock quantity");
            }
            int purchasedQuantity = requestDTO.getQuantity();

            // Check if the requested quantity is available
            int availableQuantity = stock.getQuantity();
            if (availableQuantity < purchasedQuantity) {
                return ResponseEntity.badRequest().body("Insufficient stock quantity");
            }
    
            userPortfolioService.buyStock(user, stock, purchasedQuantity);
    
            // Update the stock quantity
            int updatedQuantity = availableQuantity - purchasedQuantity;
            stock.setQuantity(updatedQuantity);
            stockRepository.save(stock);
    
            return ResponseEntity.ok("Stock bought successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error buying stock");
        }
    }

    @GetMapping("/{userId}/portfolio")
    public ResponseEntity<?> getUserPortfolio(@PathVariable("userId") Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
    
            Portfolio portfolio = portfolioRepository.findByUser(user)
                    .orElseThrow(() -> new EntityNotFoundException("Portfolio not found"));
    
            Map<String, Integer> stockMap = new HashMap<>();
            for (Stock stock : portfolio.getStocks()) {
                stockMap.put(stock.getSymbol(), stock.getQuantity());
            }
    
            PortfolioDTO portfolioDTO = new PortfolioDTO();
            portfolioDTO.setPortfolioID(portfolio.getPortfolioID());
            portfolioDTO.setUserID(user.getID());
            portfolioDTO.setStocks(stockMap);
    
            return ResponseEntity.ok(portfolioDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving user portfolio");
        }
    }
    
}
