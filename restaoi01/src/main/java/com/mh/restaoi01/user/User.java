package com.mh.restaoi01.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    private String password;
    @Column(unique = true)
    private String nickname;

    @Column(length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    private gender gender;

    private String birthdate;
    @Column(unique = true)
    private String phonenumber;
    private float height;
    private float weight;
    private LocalDateTime userdate;

    @Enumerated(EnumType.STRING)
    private resign resign;



}

