package com.mavincci.zeretest.zaretest.controllers;

import com.mavincci.zeretest.zaretest.dtos.LoginDto;
import com.mavincci.zeretest.zaretest.dtos.RegisterDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
   @PostMapping("/register")
   public RegisterDto register(@RequestBody RegisterDto req) {
      return req;
   }

   @PostMapping("/login")
   public LoginDto login(@RequestBody LoginDto req) {
      return req;
   }
}
