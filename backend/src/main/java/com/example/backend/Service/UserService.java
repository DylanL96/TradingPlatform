package com.example.backend.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.backend.Models.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {
  private List<User> store = new ArrayList<>();

  public UserService(){
    store.add(new User(UUID.randomUUID().toString(), "Dylan", "dylan@hotmail.com"));
    store.add(new User(UUID.randomUUID().toString(), "Jim", "jim@hotmail.com"));
    store.add(new User(UUID.randomUUID().toString(), "Mike", "mike@hotmail.com"));
    store.add(new User(UUID.randomUUID().toString(), "Steve", "steve@hotmail.com"));
  }

  public List<User> getUsers(){
    return this.store;
  }
}
