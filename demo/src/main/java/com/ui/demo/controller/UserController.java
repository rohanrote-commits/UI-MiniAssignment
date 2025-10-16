package com.ui.demo.controller;

import com.ui.demo.dto.LoginDto;
import com.ui.demo.dto.UserSignUpDto;
import com.ui.demo.entity.User;
import com.ui.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ui/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User signUp(@RequestBody UserSignUpDto user) {
       return userService.signUp(user);

    }
    @PostMapping("/login")
    public User login(@RequestBody LoginDto loginDto){

        return userService.login(loginDto);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
