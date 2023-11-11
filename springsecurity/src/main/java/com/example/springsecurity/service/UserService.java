package com.example.springsecurity.service;

import com.example.springsecurity.entity.User;
import com.example.springsecurity.entity.UserAuthorities;
import com.example.springsecurity.entity.UserVO;
import com.example.springsecurity.mapper.UserAutoritiesMapper;
import com.example.springsecurity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAutoritiesMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO user = new UserVO();
        User u = userMapper.userGet(username);
        UserAuthorities a = authMapper.authGet(u.getAuthorities());

        List<UserAuthorities> authorities = new ArrayList<>();
        authorities.add(a);

        user.setUsername(u.getUsername());
        user.setPassword(u.getPassword());
        user.setAuthorities(authorities);
        user.setAccountNonExpired(u.isAccountNonExpired());
        user.setAccountNonLocked(u.isAccountNonLocked());
        user.setCredentialsNonExpired(u.isCredentialsNonExpired());
        user.setEnabled(u.isEnabled());

        return user;
    }
}
