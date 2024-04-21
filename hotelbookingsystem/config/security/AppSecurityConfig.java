package com.finalproject.hotelbookingsystem.config.security;

import com.finalproject.hotelbookingsystem.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    private final JwtFilter jwtFilter;

    private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    public AppSecurityConfig(JwtFilter jwtFilter, CustomUserDetailsService customUserDetailsService) {
        this.jwtFilter = jwtFilter;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(e->e.disable());
        httpSecurity.cors(e->e.disable());
        httpSecurity.authorizeHttpRequests(authRequest ->{
                    authRequest
                            .requestMatchers("/user-api/v1/users","/user-api/v1/users/login").permitAll()

                            .requestMatchers(HttpMethod.GET,"/hotel-api/v1/**").hasAnyAuthority("ADMIN","CUSTOMER")
                            .requestMatchers(HttpMethod.DELETE,"/hotel-api/v1/**").hasAnyAuthority("ADMIN")
                            .requestMatchers(HttpMethod.POST,"/hotel-api/v1/**").hasAnyAuthority("ADMIN")
                            .requestMatchers(HttpMethod.PUT,"/hotel-api/v1/**").hasAnyAuthority("ADMIN")

                            .requestMatchers(HttpMethod.PUT,"/room-api/v1/**").hasAnyAuthority("ADMIN")
                            .requestMatchers(HttpMethod.POST,"/room-api/v1/**").hasAnyAuthority("ADMIN")
                            .requestMatchers(HttpMethod.DELETE,"/room-api/v1/**").hasAnyAuthority("ADMIN")
                            .requestMatchers(HttpMethod.GET,"/room-api/v1/**").hasAnyAuthority("ADMIN","CUSTOMER")

                            .requestMatchers(HttpMethod.GET,"/booking-api/v1/**").hasAnyAuthority("ADMIN")
                            .requestMatchers(HttpMethod.PUT,"/booking-api/v1/**").hasAnyAuthority("ADMIN","CUSTOMER")
                            .requestMatchers(HttpMethod.POST,"/booking-api/v1/**").hasAnyAuthority("ADMIN","CUSTOMER")
                            .requestMatchers(HttpMethod.DELETE,"/booking-api/v1/**").hasAnyAuthority("ADMIN","CUSTOMER")

                            .requestMatchers(HttpMethod.DELETE,"/customer-api/v1/**").hasAnyAuthority("ADMIN","CUSTOMER")
                            .requestMatchers(HttpMethod.GET,"/customer-api/v1/**").hasAnyAuthority("ADMIN")
                            .requestMatchers(HttpMethod.PUT,"/customer-api/v1/**").hasAnyAuthority("ADMIN","CUSTOMER")
                            .requestMatchers(HttpMethod.POST,"/customer/api/v1**").hasAnyAuthority("ADMIN","CUSTOMER")
                            .anyRequest().authenticated();
                }).sessionManagement(Customizer.withDefaults())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(daoAuthenticationProvider())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        System.out.println("custom service object "+customUserDetailsService);
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return  daoAuthenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(List<AuthenticationProvider> authenticationProviders) {
        return new ProviderManager(authenticationProviders);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}