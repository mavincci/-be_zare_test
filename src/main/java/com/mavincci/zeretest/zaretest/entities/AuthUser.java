package com.mavincci.zeretest.zaretest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthUser implements UserDetails {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long id;

   private String username;

   private String password;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of();
   }

   @Override
   public @Nullable String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }
}
