package com.app.todo.services;

import com.app.todo.model.FacebookPrincipal;
import com.app.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class MyOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private OAuth2UserService<OAuth2UserRequest, OAuth2User> myOAuth2UserService;

    @Autowired
    private UserRepository userRepository;

    public MyOAuth2UserService(){
        System.out.println("MyOAuth2UserService");
        myOAuth2UserService = new DefaultOAuth2UserService();
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2Useruser = myOAuth2UserService.loadUser(userRequest);
        FacebookPrincipal facebookPrincipal = new FacebookPrincipal(oAuth2Useruser);



        return facebookPrincipal;
    }
}
