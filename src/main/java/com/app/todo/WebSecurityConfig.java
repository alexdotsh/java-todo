package com.app.todo;

import com.app.todo.services.OAuth2UserServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private OAuth2UserServiceImp oAuth2UserServiceImp;
    private UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(OAuth2UserServiceImp oAuth2UserServiceImp, @Qualifier("userDetailsServiceImp") UserDetailsService userDetailsService) {
        this.oAuth2UserServiceImp = oAuth2UserServiceImp;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

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
