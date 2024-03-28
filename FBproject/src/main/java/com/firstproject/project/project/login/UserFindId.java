package com.firstproject.project.project.login;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(title = "아이디 찾을때 필요한 정보")
public class UserFindId {

    @Schema(title = "사용자 이름")
    private String name;
    @Schema(title = "사용자 이메일")
    private String email;
}
