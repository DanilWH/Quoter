package com.example.Quoter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.example.Quoter.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//  @Autowired
//  private DataSource dataSource; // DataSource is generated by Spring.
    
    @Autowired
    private UserService userService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/registration").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
                .logout()
                .permitAll();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(NoOpPasswordEncoder.getInstance()); // encrypts a user password.
    }

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource) // is needed to allow the manager to access to the database
                                    // and, in there, find users and their roles.
            .passwordEncoder(NoOpPasswordEncoder.getInstance()) // encrypts a user password.
            // allows the system to get users by their username.
            .usersByUsernameQuery("SELECT username, password, active FROM usr WHERE username=?")
            .authoritiesByUsernameQuery("SELECT u.username, ur.roles FROM usr u INNER JOIN user_role ur ON u.id = ur.user_id WHERE u.username=?");
    }
    */
}