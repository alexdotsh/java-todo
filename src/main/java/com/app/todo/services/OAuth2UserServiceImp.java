package com.app.todo.services;

import com.app.todo.model.FacebookUser;
import com.app.todo.model.User;
import com.app.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class MyOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private OAuth2UserService<OAuth2UserRequest, OAuth2User> myOAuth2UserService;

    @Autowired
    private UserRepository userRepository;

    public MyOAuth2UserService(){
        myOAuth2UserService = new DefaultOAuth2UserService();
    }

    public MyOAuth2UserService(OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService ){
        myOAuth2UserService = oAuth2UserService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2Useruser = myOAuth2UserService.loadUser(userRequest);

        Map userAttributes = oAuth2Useruser.getAttributes();

        Optional<User> user = userRepository.findByTypeAndExternalId("Facebook", (String)userAttributes.get("id"));

        if (user.isPresent()) {
            return (FacebookUser)user.get();
        } else {
            FacebookUser newUser = new FacebookUser(oAuth2Useruser);
            userRepository.save(newUser);
            return newUser;
        }
    }
}
