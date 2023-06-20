package com.example.backend.Controller;

import java.util.List;

import com.example.backend.Models.User;
import com.example.backend.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

  @Autowired
  private UserService userService;

  @GetMapping("/user")
  public List<User> getUser(){
    System.out.println("Getting users");
    return this.userService.getUsers();
  }
}
