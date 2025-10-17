package com.ui.demo.service;

import com.ui.demo.dto.LoginDto;
import com.ui.demo.dto.UserSignUpDto;
import com.ui.demo.entity.User;
import com.ui.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.PageRequest.*;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<?> signUp(UserSignUpDto userSignUpDto) {
        if(userSignUpDto.getPassword().equals(userSignUpDto.getConfirmPassword()) == false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords do not match");
        }
            if(userRepo.existsByEmail(userSignUpDto.getEmail())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
            }
        User user = new User();
        user.setName(userSignUpDto.getName());
        user.setEmail(userSignUpDto.getEmail());
        user.setPassword(userSignUpDto.getPassword());
        user.setPhoneNumber(userSignUpDto.getPhoneNumber());
        user.setCountryCode(userSignUpDto.getCountryCode());
        return ResponseEntity.ok(userRepo.save(user));
    }


    public User login(LoginDto loginDto){
        User user = userRepo.findByEmailAndPassword(loginDto.getEmail(),loginDto.getPassword());
       return user;
    }

    public Page<User> getAllUsers(int page, int size){
        return userRepo.findAll(of(page,size));
    }
}
