package com.milkedu.milkedu.service;

import com.milkedu.milkedu.entity.User;
import com.milkedu.milkedu.entity.UserAuthorities;
import com.milkedu.milkedu.entity.UserVO;
import com.milkedu.milkedu.mapper.UserAutoritiesMapper;
import com.milkedu.milkedu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
