package com.mh.restaoi01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class WebConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // post 방식 put 방식 delete 방식을 사용할때 csrf 토큰을 사용하지 않겠다.
        // 허용해주겠다..
        http.csrf(httpSecurityCsrfConfigurer ->  httpSecurityCsrfConfigurer.disable() );
        http
                .authorizeRequests(
//                       req ->
//                            req.requestMatchers("/member","test").permitAll().
//                            anyRequest().authenticated()
                        req->req.anyRequest().permitAll()
                );

        // Exceptionhandling ->
        // 스프링시큐리티에서 필터를 다는 방법

        // http.addFilter(filter);

        // 인터셉터
        // http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



}
