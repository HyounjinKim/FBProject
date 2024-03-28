package com.firstproject.project.project.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "로그인에 필요한 정보")
public class LoginInfo {
    
    @Schema(title = "사용자 아이디")
    private String id;
    @Schema(title = "사용자 비밀번호")
    private String password;
    @Schema(title = "탈퇴여부")
    private Resign resign;
}
