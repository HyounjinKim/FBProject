package com.firstproject.project.project.conf;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "운동,음식 칼로리 기록",
<<<<<<< HEAD
        description = "로그인,회원가입에 관한 스웨거 문서",
        version = "v1.0.0"),servers = @Server(url = "/login/**")
=======
                description = "로그인,회원가입에 관한 스웨거 문서",
                version = "v1.0.0")
>>>>>>> 10a7f1a13a89ced8ba1de36c58c1ec0dabf0dfbf
)

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi loginApi(){
        String[] path = {"/login/**","/user/**","/main/**"};

        return GroupedOpenApi.builder()
                .group("login")
                .pathsToMatch(path)
                .build();
    }
}