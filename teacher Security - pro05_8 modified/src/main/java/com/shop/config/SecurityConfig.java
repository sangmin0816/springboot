package com.shop.config;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // cors, csrf, logout, formLogin, httpBasic, exceptionHandling, authorizeHttpRequests
        http.formLogin((formLogin) ->
            formLogin
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginPage("/authentication/login")
                    .failureForwardUrl("/authentication/login?failed")
                    .loginProcessingUrl("/authentication/login/process")
        );

        http.logout((logout)->
                logout.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                        .logoutSuccessUrl("/")
        );


        http.exceptionHandling((exception)->
                exception
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 인증 없을 시 처리
                        .accessDeniedPage("/error/access-denied") // 권한 없을 시 이동할 페이지
        );


        http.authorizeRequests((authorize)->
                authorize
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                        .requestMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){

    }
}