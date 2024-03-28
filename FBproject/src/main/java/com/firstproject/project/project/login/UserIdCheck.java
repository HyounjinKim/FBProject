package com.firstproject.project.project.login;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(title = "아이디 중복체크")
public class UserIdCheck {

    @Schema(title = "사용 할 아이디")
    private String idCheck;
}
