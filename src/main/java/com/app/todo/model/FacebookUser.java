package com.app.todo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Entity
@DiscriminatorValue(value = "Facebook")
public class FacebookUser extends User implements MyPrincipal, OAuth2User{

    public FacebookUser(){}

    public FacebookUser(OAuth2User oAuth2User) {
        this.setUsername((String)oAuth2User.getAttributes().get("name"));
        this.setExternalId((String)oAuth2User.getAttributes().get("id"));

    }

    private String Name;
    @Override
    public String getLogin() {
        return getUsername();
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
        return getUsername();
    }
}
