package com.bhupi.demo.service;

import com.bhupi.demo.model.User;
import com.bhupi.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl {
    @Autowired
    UserRepo userRepo;
    public List<User> getUsers(){
        List<User> users=userRepo.findAll();
        return users;
    }
    public User addUser(User user){
        return userRepo.save(user);
    }
}
