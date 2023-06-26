package com.example.backend.Service;

import com.example.backend.DTO.SignupRequest;
import com.example.backend.DTO.UserDTO;
import com.example.backend.Models.Portfolio;
import com.example.backend.Models.User;
import com.example.backend.Repositories.PortfolioRepository;
import com.example.backend.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
  @Autowired
  private UserRepository userRepository;
  private PortfolioRepository portfolioRepository;

  @Autowired
  public AuthServiceImpl(UserRepository userRepository, PortfolioRepository portfolioRepository) {
      this.userRepository = userRepository;
      this.portfolioRepository = portfolioRepository;
  }

  @Override
  public UserDTO createUser(SignupRequest signupRequest) {
      User user = new User();
      user.setEmail(signupRequest.getEmail());
      user.setName(signupRequest.getName());
      user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));

      // Save the user first to generate a user ID
      User createdUser = userRepository.save(user);

      // Create a new portfolio for the user
      Portfolio portfolio = new Portfolio();
      portfolio.setUser(createdUser);

      // Save the portfolio
      portfolioRepository.save(portfolio);

      // Associate the portfolio with the user
      createdUser.setPortfolio(portfolio);

      // Save the user again to update the portfolio association
      userRepository.save(createdUser);

      UserDTO userDTO = new UserDTO();
      userDTO.setID(createdUser.getID());
      userDTO.setEmail(createdUser.getEmail());
      userDTO.setName(createdUser.getName());
      return userDTO;
  }
}
