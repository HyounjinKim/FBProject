package com.firstproject.project.project.login;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(title = "비밀번호 찾을때 필요한 정보")
public class UserFindPassword {

    @Schema(title = "사용자 아이디")
    private String id;
    @Schema(title = "사용자 이메일")
    private String email;
}
