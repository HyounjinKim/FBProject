package com.firstproject.project.project.login;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "User")
@Schema(title = "유저 정보",description = "user table에 관한 내용")
@DynamicInsert
public class User {

    @Id
    @Schema(title = "유저 아이디")
    private String id;

    @Column(nullable = false)
    @Schema(title = "비밀번호")
    private String password;

    @Transient
    @Schema(title = "비밀번호 확인")
    private String passwordch;

    @Column(unique = true,nullable = false)
    @Schema(title = "이메일")
    private String email;

    @Column(unique = true,nullable = false)
    @Schema(title = "닉네임")
    private String nickname;

    @Column(nullable = false)
    @Schema(title = "이름")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(title = "성별")
    private Gender gender;

    @Column(nullable = false)
    @Schema(title = "생년월일")
    private String birthdate;

    @Column(unique = true,nullable = false)
    @Schema(title = "휴대폰번호")
    private String phonenumber;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Schema(title = "회원가입 시간")
    private LocalDateTime user_date;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'N'")
    @Schema(title = "탈퇴여부")
    private Resign resign;

    @Schema(title = "키")
    private float height;
    @Schema(title = "몸무게")
    private float weight;
}
