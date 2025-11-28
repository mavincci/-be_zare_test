package com.mavincci.zeretest.zaretest.repositories;

import com.mavincci.zeretest.zaretest.entities.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
   Optional<AuthUser> findByUsername(String username);
}
