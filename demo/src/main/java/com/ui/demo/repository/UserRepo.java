package com.ui.demo.repository;

import com.ui.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
    Page<User> findAll(Pageable pageable);
}
