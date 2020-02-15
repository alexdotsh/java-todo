package com.app.todo;

import com.app.todo.services.MyOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MyOAuth2UserService myOAuth2UserService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.inMemoryAuthentication().withUser("domin").password(passwordEncoder().encode("ddd")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("ravan").password("ravan123").roles("USER");
        auth.inMemoryAuthentication().withUser("kans").password("kans123").roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.debug(trur);
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers(
//                        "/resources/**",
//                        "/login/registration",
//                        "/registration/**",
//                        "/registration",
//                        "/login/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/users/login")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/users/logout")
//                .deleteCookies("JSESSIONID")
//                .permitAll();

        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                //.userDetailsService(userDetailsService)
                .userInfoEndpoint()
                .userService(myOAuth2UserService);
                //.redirectionEndpoint()
                //.baseUri("/?aaaa=3");

    }





}

