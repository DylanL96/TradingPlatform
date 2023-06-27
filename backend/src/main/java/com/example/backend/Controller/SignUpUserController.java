package com.example.backend.Controller;

import com.example.backend.DTO.SignupRequest;
import com.example.backend.DTO.UserDTO;
import com.example.backend.Service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class SignUpUserController {
  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest){
      UserDTO createdUser = authService.createUser(signupRequest);
      if(createdUser == null){
        return new ResponseEntity<>("User not created. Try again", HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }
}
