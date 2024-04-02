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

        if (request.getRequestURI().contains("swagger")
                || request.getRequestURI().contains("v3")) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            String credentials = new String(Base64.getDecoder().decode(authHeader.substring(6)));
            String[] values = credentials.split(":", 2);
            String userid = values[0];
            String password = values[1];

            User user = loginRepository.findByIdAndPassword(userid, password);

            if (user != null) {
                return true;
            }
        }
        return false;
    }
}

