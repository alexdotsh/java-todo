package com.app.todo;

import com.app.todo.services.OAuth2UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2UserServiceImp oAuth2UserServiceImp;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { return new BCryptPasswordEncoder(); }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/registration", "/login", "/webjars/**").permitAll()
            .anyRequest().authenticated()
            .and()
                .formLogin()
            .and()
                .oauth2Login()
            .userInfoEndpoint()
            .userService(oAuth2UserServiceImp)
            .and()
            .and()
            .logout()
            .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
            .deleteCookies("JSESSIONID");
    }
}
