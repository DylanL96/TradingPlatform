package com.example.backend.Service.Jwt;

import java.util.ArrayList;

import com.example.backend.Models.User;
import com.example.backend.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

  @Autowired
  private UserRepository userRepository;

  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findFirstByEmail(email);
    if(user == null){
      throw new UsernameNotFoundException("User not found", null);
    }
    return new org.springframework.security.core.userdetails.User(user.getEmail(), 
    user.getPassword(), new ArrayList<>());

  }
  
}
