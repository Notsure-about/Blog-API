package com.example.Blog_API.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
private final JwtAuthFilter jwtAuthFilter;
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .authorizeHttpRequests(auth->auth.requestMatchers("/api/comments/**","/auth/**").permitAll()
//                        .requestMatchers("/api/users/**").hasRole("USER")
//                        .requestMatchers("/api/category/**").hasAnyRole("USER", "CATEGORY")//authenticated()//only authenticated users can access the db
                        .requestMatchers("/api/entity/**").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class);
//                .formLogin(Customizer.withDefaults());// we can config this but frontend should do form login (backend should do the api things only)
     return httpSecurity.build(); // it will give access to every authorization let see how do we prevent it
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}
