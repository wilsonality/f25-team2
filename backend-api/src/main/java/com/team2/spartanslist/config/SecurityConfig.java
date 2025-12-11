package com.team2.spartanslist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/", "/home", "/css/**", "/js/**", "/images/**", "/register", "/sellers", "/shoppers").permitAll()
            .requestMatchers("/offers/**", "/reviews/**", "/orders/**", "/carts/**").hasAnyRole("SELLER", "SHOPPER")
            .requestMatchers(HttpMethod.POST,"/offers/**","/sellers/**").hasAnyRole("SELLER")
            .requestMatchers(HttpMethod.POST,"/shoppers/**").hasAnyRole("SHOPPER")
            .requestMatchers("/reviews/**").hasAnyRole("SELLER", "SHOPPER")
            .requestMatchers("/shoppers/*/update").hasAnyRole("SHOPPER")
            .anyRequest().authenticated())
        .formLogin(form -> form
            .loginPage("/login")
            .permitAll())
        .exceptionHandling((x) -> x.accessDeniedPage("/403"))
        .logout(withDefaults());
    return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}