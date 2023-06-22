package com.example.backend.Service;

import com.example.backend.DTO.SignupRequest;
import com.example.backend.DTO.UserDTO;
import com.example.backend.Models.User;
import com.example.backend.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDTO createUser(SignupRequest signupRequest) {
    User user = new User();
    user.setEmail(signupRequest.getEmail());
    user.setName(signupRequest.getName());
    user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
    User createdUser = userRepository.save(user);

    UserDTO userDTO = new UserDTO();
    userDTO.setID(createdUser.getID());
    userDTO.setEmail(createdUser.getEmail());
    userDTO.setName(createdUser.getName());
    return userDTO;
  }
}
