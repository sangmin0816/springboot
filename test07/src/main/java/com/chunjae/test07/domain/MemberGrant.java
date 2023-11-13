package com.chunjae.test07.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class MemberGrant implements GrantedAuthority {
    String authority;
}
