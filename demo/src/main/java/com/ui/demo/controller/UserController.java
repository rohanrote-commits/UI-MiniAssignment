package com.ui.demo.controller;

import com.ui.demo.dto.LoginDto;
import com.ui.demo.dto.UserSignUpDto;
import com.ui.demo.entity.User;
import com.ui.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/ui/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserSignUpDto user) {
       return userService.signUp(user);

    }
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDto loginDto, HttpSession session){


        User user = userService.login(loginDto);
        if(user != null) {
            session.setAttribute("user",loginDto);
            session.setMaxInactiveInterval(15*60);

            return ResponseEntity.ok(user);
        }
        else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/all-data")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam(defaultValue = "0") int page
    , @RequestParam(defaultValue = "10") int size){
       Page<User> userPage =  userService.getAllUsers(page,size);
       return ResponseEntity.ok(userPage);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

}
