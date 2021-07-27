package com.example.springbasicauth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

@RequiredArgsConstructor

/**
 * Here is the security config class
 */
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//disable Cross Site Reference Forgery protection for the purposes of this demo
                .authorizeRequests()
                .antMatchers("/", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/sell/**").hasRole("SELLER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(); //here we specify basic auth
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails buyer = User.builder()
                .username("car_buyer")
                .password(passwordEncoder.encode("buy_today"))
                .roles("BUYER") //ROLE_BUYER
                .build();

        UserDetails seller = User.builder()
                .username("car_seller")
                .password(passwordEncoder.encode("sell_today"))
                .roles("SELLER") //ROLE_SELLER
                .build();

        return new InMemoryUserDetailsManager(
                buyer,
                seller
        );
    }
}
