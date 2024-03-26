package com.mh.restapi03.friend;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int fidx;

//    // 외래키 설정, user 클래스의 nickname에 @Column(nullable = false, unique = true) 를 첨가해야함.
//    @ManyToOne
//    @JoinColumn(name = "nickname", referencedColumnName = "nickname")
    private String nickname;


    private String fnick;
    private Approve approve;


}
