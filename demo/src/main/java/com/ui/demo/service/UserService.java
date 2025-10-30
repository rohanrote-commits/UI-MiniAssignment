package com.ui.demo.service;

import com.ui.demo.dto.LoginDto;
import com.ui.demo.dto.UserSignUpDto;
import com.ui.demo.entity.User;
import com.ui.demo.entity.UserSession;
import com.ui.demo.repository.UserRepo;
import com.ui.demo.repository.UserSessionRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.domain.PageRequest.*;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserSessionRepository userSessionRepository;

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


    @Transactional
    public ResponseEntity<?> login(LoginDto loginDto){
        User user = userRepo.findByEmailAndPassword(loginDto.getEmail(),loginDto.getPassword());
        if(user != null) {

            if(userSessionRepository.existsUserSessionByEmail(loginDto.getEmail())){
                userSessionRepository.deleteUserSessionByEmail(loginDto.getEmail());
            }
//           String token = UUID.randomUUID().toString();
//hello
            UserSession session = new UserSession();
            session.setEmail(user.getEmail());
            session.setToken(token);
            session.setCreatedAt(LocalDateTime.now());
            session.setExpiresAt(LocalDateTime.now().plusMinutes(15));
            userSessionRepository.save(session);

            return ResponseEntity.ok(token);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    public Page<User> getAllUsers(int page, int size){

        return userRepo.findAll(of(page,size));
    }

    @Transactional
    public void logout(String token){
        userSessionRepository.deleteByToken(token);
    }
}
