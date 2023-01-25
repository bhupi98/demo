package com.bhupi.demo.controller;

import com.bhupi.demo.model.User;
import com.bhupi.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/users")
    ResponseEntity<List<?>> users(){
        try{
            List<User>list=userService.getUsers();
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch(HttpServerErrorException.InternalServerError e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addUser")
    ResponseEntity<User> saveBook(@RequestBody User user){
        try{
            User saveUser=userService.addUser(user);
            System.out.println("Book is being saved"+saveUser);
            return new ResponseEntity<User>(saveUser,HttpStatus.OK);
        }catch (HttpServerErrorException.BadGateway e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

}
