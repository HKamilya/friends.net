package ru.itis.javalab.security.details;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.model.User;


import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private User userEntity;

    @Autowired
    private ModelMapper modelMapper;

    public UserDetailsImpl(User userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getRole().toString());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return userEntity.getId();
    }

    public UserDto getUserDto() {
        System.out.println(userEntity);
        return modelMapper.map(userEntity, UserDto.class);
    }
}
