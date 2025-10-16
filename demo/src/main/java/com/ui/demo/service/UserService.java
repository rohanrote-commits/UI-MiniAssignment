package com.ui.demo.service;

import com.ui.demo.dto.LoginDto;
import com.ui.demo.dto.UserSignUpDto;
import com.ui.demo.entity.User;
import com.ui.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User signUp(UserSignUpDto userSignUpDto) {
        if(userSignUpDto.getPassword().equals(userSignUpDto.getConfirmPassword()) == false){
            return null;
        }

        User user = new User();
        user.setName(userSignUpDto.getName());
        user.setEmail(userSignUpDto.getEmail());
        user.setPassword(userSignUpDto.getPassword());
        user.setPhoneNumber(userSignUpDto.getPhoneNumber());
        user.setCountryCode(userSignUpDto.getCountryCode());
        return userRepo.save(user);
    }


    public User login(LoginDto loginDto){
        User user = userRepo.findByEmailAndPassword(loginDto.getEmail(),loginDto.getPassword());
       return user;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
}
