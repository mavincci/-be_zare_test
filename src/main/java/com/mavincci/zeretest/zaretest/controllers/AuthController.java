package com.mavincci.zeretest.zaretest.controllers;

import com.mavincci.zeretest.zaretest.dtos.AuthSessionDto;
import com.mavincci.zeretest.zaretest.dtos.LoginDto;
import com.mavincci.zeretest.zaretest.dtos.RegisterDto;
import com.mavincci.zeretest.zaretest.entities.AuthUser;
import com.mavincci.zeretest.zaretest.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
   private final AuthService authService;

   @PostMapping("/register")
   public AuthUser register(@RequestBody RegisterDto req) throws Exception {
      return authService.register(req);
   }

   @PostMapping("/login")
   public AuthSessionDto login(@RequestBody LoginDto req) {
      return authService.login(req);
   }
}
