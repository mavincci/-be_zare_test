package com.mavincci.zeretest.zaretest.configuration;

import com.mavincci.zeretest.zaretest.services.AuthUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
@RequiredArgsConstructor
public class SecurityConfiguration {

   private final AuthUserDetailService authUserDetailService;
   private final JwtFilter jwtFilter;

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
      security
            .httpBasic(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults())
            .sessionManagement(m -> m.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(r ->
                  r
                        .requestMatchers(
                              "/auth/register",
                              "/auth/login"
                        ).permitAll()
                        .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(
                  jwtFilter, UsernamePasswordAuthenticationFilter.class
            )
            .formLogin(AbstractHttpConfigurer::disable);

      return security.build();
   }

   @Bean
   public AuthenticationProvider authenticationProvider() {
      final var daoAuthProvider = new DaoAuthenticationProvider(authUserDetailService);
      daoAuthProvider.setPasswordEncoder(passwordEncoder());
      return daoAuthProvider;
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public AuthenticationManager authenticationManager(
         AuthenticationConfiguration authenticationConfiguration)
         throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
   }
}
