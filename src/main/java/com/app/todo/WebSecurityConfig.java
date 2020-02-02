package com.app.todo;

import com.app.todo.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.*;
import java.io.IOException;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.inMemoryAuthentication().withUser("domin").password(passwordEncoder().encode("ddd")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("ravan").password("ravan123").roles("USER");
        auth.inMemoryAuthentication().withUser("kans").password("kans123").roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.debug(trur);
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/resources/**",
                        "/login/registration",
                        "/registration/**",
                        "/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/users/logout")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }


//.csrf().disable()
//          .authorizeRequests()
//          .antMatchers("/admin/**").hasRole("ADMIN")
//          .antMatchers("/anonymous*").anonymous()
//          .antMatchers("/login*").permitAll()
//          .anyRequest().authenticated()
//          .and()
//          .formLogin()
//          .loginPage("/login.html")
//          .loginProcessingUrl("/perform_login")
//          .defaultSuccessUrl("/homepage.html", true)
//    //.failureUrl("/login.html?error=true")
//          .failureHandler(authenticationFailureHandler())
//            .and()
//          .logout()
//          .logoutUrl("/perform_logout")
//          .deleteCookies("JSESSIONID")
//          .logoutSuccessHandler(logoutSuccessHandler());



}

