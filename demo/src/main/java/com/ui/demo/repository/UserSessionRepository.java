package com.ui.demo.repository;

import com.ui.demo.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    Optional<UserSession> findByToken(String token);

    @Modifying
    void deleteByToken(String token);

    boolean existsUserSessionByEmail(String email);

    void deleteUserSessionByEmail(String email);
}
