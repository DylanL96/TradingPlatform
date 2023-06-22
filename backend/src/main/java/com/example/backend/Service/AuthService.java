package com.example.backend.Service;

import com.example.backend.DTO.SignupRequest;
import com.example.backend.DTO.UserDTO;

public interface AuthService {
  UserDTO createUser(SignupRequest signupRequest);
}
