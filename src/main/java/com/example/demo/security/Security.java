package com.example.demo.security;

import com.example.demo.dao.UserDao;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserDao.class)
@Configuration
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
//        auth.userDetailsService(userService.getByUsername(username));
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            //.antMatchers("/**") for specific requests
            .anyRequest()
            .permitAll()
            .and().httpBasic();
        http.csrf().disable();
    }
}
