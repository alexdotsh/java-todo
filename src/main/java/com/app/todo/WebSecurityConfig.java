package com.app.todo;

import com.app.todo.services.MyOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyOAuth2UserService myOAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/registration", "/", "/login**").permitAll()
            .anyRequest().authenticated()
            .and()
                .formLogin()
            .and()
                .oauth2Login()
            .userInfoEndpoint()
            .userService(myOAuth2UserService)
            .and()
            .and()
            .logout()
            .logoutUrl("/users/logout")
            .deleteCookies("JSESSIONID");
    }
}
