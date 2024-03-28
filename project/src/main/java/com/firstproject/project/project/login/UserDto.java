package com.firstproject.project.project.login;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@DynamicInsert
@Schema(title = "DB 유효성 검사하는 유저정보")
public class UserDto {

    @Id
    @Schema(title = "유저 아이디")
    @NotBlank
    private String id;

    @Size(min = 8, max = 15)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,15}", message = "비밀번호는 8~15자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @Schema(title = "비밀번호")
    @NotBlank
    private String password;

    @Schema(title = "비밀번호 확인")
    private String passwordch;

    @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$", message = "이메일을 확인해 주세요.")
    @Schema(title = "이메일")
    @NotBlank
    private String email;

    @NotBlank
    @Schema(title = "닉네임")
    private String nickname;

    @Schema(title = "이름")
    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    @Schema(title = "성별")
    private Gender gender;

    @Size(min = 6, max = 6, message = "생년월일을 확인해 주세요.")
    @Schema(title = "생년월일")
    @NotBlank
    private String birthdate;

    @Size(min = 11, max = 11, message = "휴대폰번호를 확인해 주세요.")
    @Schema(title = "휴대폰번호")
    @NotBlank
    private String phonenumber;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Schema(title = "회원가입 시간")
    private LocalDateTime user_date;

    @Enumerated(EnumType.STRING)
    @Schema(title = "탈퇴여부")
    private Resign resign;

    @Schema(title = "키")
    private float height;
    @Schema(title = "몸무게")
    private float weight;

}
