package com.mavincci.zeretest.zaretest.services;

import com.mavincci.zeretest.zaretest.configuration.JwtUtils;
import com.mavincci.zeretest.zaretest.dtos.AuthSessionDto;
import com.mavincci.zeretest.zaretest.dtos.LoginDto;
import com.mavincci.zeretest.zaretest.dtos.RegisterDto;
import com.mavincci.zeretest.zaretest.entities.AuthUser;
import com.mavincci.zeretest.zaretest.repositories.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
   private final AuthUserRepository authUserRepository;
   private final AuthenticationManager authenticationManager;

   private final JwtUtils jwtUtils;
   private final PasswordEncoder passwordEncoder;

   public AuthUser register(RegisterDto req) throws Exception {
      final var existingUser = authUserRepository.findByUsername(req.username().trim());
      
      if(existingUser.isPresent())
         throw new Exception("USERNAME_ALREADY_EXISTS");
      
      final var tempUser = AuthUser.builder()
            .username(req.username().trim())
            .password(passwordEncoder.encode(req.password().trim()))
            .build();
      
      final var savedUser = authUserRepository.save(tempUser);
      
      return savedUser;
   }

   public AuthSessionDto login(LoginDto req) {
      authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                  req.username().trim(), req.password().trim()));

      final var authenticatedUser = authUserRepository.findByUsername(req.username().trim()).get();

      final String token = jwtUtils.generateToken(authenticatedUser);

      return new AuthSessionDto(
            token,
            authenticatedUser
      );
   }
}
