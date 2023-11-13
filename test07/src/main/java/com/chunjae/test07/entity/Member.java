package com.chunjae.test07.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
public class Member implements UserDetails {
    private List<GrantedAuthority> authorities;
    // authorities는 List가 아니라 Map, Set 등 다른 Collection이 될 수도 있다.
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    // 그 외의 사용자 정의 함수
}
