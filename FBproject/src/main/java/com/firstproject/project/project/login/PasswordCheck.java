package com.firstproject.project.project.login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Schema(name = "비밀번호 변경에 필요한 정보")
public class PasswordCheck {

    @Schema(title = "사용자 아이디")
    private String id;
    @Schema(title = "사용할 비밀번호")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,15}", message = "비밀번호는 8~15자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;
    @Schema(title = "비밀번호 확인")
    private String passwordCheck;
}
