package br.com.viceri.todo.config;

import br.com.viceri.todo.filters.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

  private final JWTFilter jwtFilter;
  public SpringSecurityConfig(JWTFilter jwtFilter) {
    this.jwtFilter = jwtFilter;
  }

  private static final String[] AUTH_WHITE_LIST = {
          "/api/auth/registro",
          "/api/auth/login",
          "swagger-ui.html",
          "/v3/api-docs/**",
          "/swagger-ui/**",
          "/v2/api-docs/**",
          "/swagger-resources/**"
  };
  @Bean
  public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
    http
            .csrf( csrf -> csrf.disable())
            .addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement( sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests( auth -> auth
                    .requestMatchers(AUTH_WHITE_LIST).permitAll()
                    .requestMatchers("/**").authenticated()
            );

    return  http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
    return authenticationConfiguration.getAuthenticationManager();

  }
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();

  }

}