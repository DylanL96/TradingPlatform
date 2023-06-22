package com.example.backend.DTO;

import lombok.Data;

@Data
public class AuthenticationRequest {
  private String email;
  private String password;
}
