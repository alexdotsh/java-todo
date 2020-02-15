package com.app.todo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FacebookPrincipal implements MyPrincipal, OAuth2User {

    private OAuth2User oAuth2User;
    public FacebookPrincipal(OAuth2User oAuth2User){
        this.oAuth2User = oAuth2User;
    }

    @Override
    public String getLogin() {
        return this.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority admin = new SimpleGrantedAuthority("ADMIN");
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(admin);
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return (String) this.oAuth2User.getAttributes().get("name");
    }
}
