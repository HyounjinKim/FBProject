package com.firstproject.project.project.conf;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "운동,음식 칼로리 기록",
                description = "로그인,회원가입에 관한 스웨거 문서",
                version = "v1.0.0")
)

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi loginApi(){
        String[] path = {"/login/**","/user/**","/main/**","/friend/**","/calender/**","/follow/**"};

        return GroupedOpenApi.builder()
                .group("project")
                .pathsToMatch(path)
                .build();
    }
}