package com.firstproject.project.project.conf;

import com.firstproject.project.project.login.User;
import com.firstproject.project.project.login.LoginRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final LoginRepository loginRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

<<<<<<< HEAD
=======
        if (request.getRequestURI().contains("swagger")
                || request.getRequestURI().contains("v3")) {
            return true;
        }

>>>>>>> 10a7f1a13a89ced8ba1de36c58c1ec0dabf0dfbf
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            // Basic Authentication 헤더에서 "username:password"를 디코딩합니다.
            String credentials = new String(Base64.getDecoder().decode(authHeader.substring(6)));
            String[] values = credentials.split(":", 2);
            String username = values[0];
            String password = values[1];

            User user = loginRepository.findByIdAndPassword(username, password);

            if (user != null) {
                return true;
            }
        }
        return false;
    }
}

