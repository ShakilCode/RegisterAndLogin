// Config spring security and access our login page without coment it
package com.example.sign.up.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.sign.up.model.AdminMyAppUserService;

import lombok.AllArgsConstructor;




@Configuration
@AllArgsConstructor
// This activate spring security for our application
@EnableWebSecurity
public class AdminSecurityConfig {

    @Autowired
    private final AdminMyAppUserService appUserService;

    @Bean
    public UserDetailsService userDetailsService(){
        return appUserService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // Encript the password
    @Bean 
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(httpForm -> {
            httpForm.loginPage("/adminlogin").permitAll();
            httpForm.defaultSuccessUrl("/index", true); // Ensure this is correct
        })
        .authorizeHttpRequests(registry -> {
            registry.requestMatchers("/req/adminsignup", "/css/**", "/js/**", "/images/**").permitAll();
            registry.anyRequest().authenticated();
        })
        .build();
}


}
