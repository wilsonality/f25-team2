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
import org.springframework.security.config.http.SessionCreationPolicy;
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
        .csrf(csrf -> csrf.disable())
          .securityContext(securityContext ->
            securityContext.requireExplicitSave(false)
        )
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
        )
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/", "/home", "/css/**", "/js/**", "/seller-pictures/**", "/shopper-pictures/**", "/offer-pictures/**", "/register", "/login", "/login/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/sellers", "/shoppers").permitAll()
            .requestMatchers(HttpMethod.POST, "/offers/**", "/sellers/**").hasRole("SELLER")
            .requestMatchers(HttpMethod.POST, "/shoppers/**").hasRole("SHOPPER")
            .requestMatchers(HttpMethod.POST, "/offers/**", "/reviews/**", "/orders/**", "/carts/**").hasAnyRole("SELLER", "SHOPPER")
            .anyRequest().authenticated())
        .formLogin(form -> form
            .loginPage("/login")
            .permitAll())
        .exceptionHandling((x) -> x.accessDeniedPage("/403"))
        .logout(withDefaults());
    return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}