package com.example.backend.Controller;

import com.example.backend.DTO.AuthenticationRequest;
import com.example.backend.DTO.AuthenticationResponse;
import com.example.backend.Repositories.UserRepository;
import com.example.backend.Service.Jwt.UserDetailsServiceImpl;
import com.example.backend.Utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
  
  @Autowired
  private JwtUtil jwtUtil;
  
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;
  
  @Autowired
  private UserRepository userRepository;
  

  @PostMapping("/authenticate")
  public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException, java.io.IOException {
      try {
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
      } catch (BadCredentialsException e) {
          throw new BadCredentialsException("Incorrect username or password!");
      } catch (DisabledException disabledException) {
          response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not created. Register User first");
          return null;
      }

      Long userId = userRepository.findIdByEmail(authenticationRequest.getEmail());
      final UserDetails userDetails = userDetailsService.loadUserByUsername(
        authenticationRequest.getEmail());

      final String jwt = jwtUtil.generateToken(userDetails.getUsername());
      return new AuthenticationResponse(jwt, userId);

  }
}
