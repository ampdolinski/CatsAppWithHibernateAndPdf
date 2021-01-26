package com.sda.cats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    @Bean
    BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
//                .inMemoryAuthentication().withUser("jacek").authorities("ROLE_USER")
//                .password(encoder().encode("jacek"))
//                .and().withUser("sylwia")
//                .password(encoder().encode("sylwia")).authorities("ROLE_ADMIN")
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/hello").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/pdf").permitAll()
                .antMatchers("/cats").permitAll()
//                .antMatchers("/cats").hasRole("ADMIN")
                .and()
                .httpBasic();
    }
}
