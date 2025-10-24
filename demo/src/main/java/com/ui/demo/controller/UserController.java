package com.ui.demo.controller;

import com.ui.demo.dto.LoginDto;
import com.ui.demo.dto.UserSignUpDto;
import com.ui.demo.entity.User;
import com.ui.demo.entity.UserSession;
import com.ui.demo.repository.UserSessionRepository;
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
@CrossOrigin(
        origins = "http://localhost:5500",
        allowCredentials = "true"
)

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserSessionRepository userSessionRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserSignUpDto user) {
       return userService.signUp(user);

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){

    return userService.login(loginDto);
    }

    @GetMapping("/all-data")
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
       Page<User> userPage =  userService.getAllUsers(page,size);
       return ResponseEntity.ok(userPage);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        //passing the token to logout method
        userService.logout(authHeader);
        return ResponseEntity.ok("Logged out successfully");
    }

}
