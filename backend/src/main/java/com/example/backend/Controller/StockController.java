package com.example.backend.Controller;

import com.example.backend.Models.Stock;
import com.example.backend.Models.User;
import com.example.backend.Repositories.StockRepository;
import com.example.backend.Repositories.UserRepository;
import com.example.backend.Service.UserPortfolioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private StockRepository stockRepository;
    
    @Autowired
    private UserPortfolioService userPortfolioService;
    
    @PostMapping("/{userId}/buy/{stockId}")
    public ResponseEntity<String> buyStock(
            @PathVariable("userId") Long userId,
            @PathVariable("stockId") Long stockId,
            @RequestParam("quantity") int quantity) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundException());
            Stock stock = stockRepository.findById(stockId)
                    .orElseThrow(() -> new NotFoundException());

            userPortfolioService.buyStock(user, stock, quantity);
            
            return ResponseEntity.ok("Stock bought successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error buying stock");
        }
    }
}
