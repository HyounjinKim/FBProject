package com.mh.restapi03.friend;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FriendDto {
    private int fidx;
    private String nickname;
    private String fnick;
    private Approve approve;

    public FriendDto(int fidx, String nickname, String fnick, Approve approve) {
        this.fidx = fidx;
        this.nickname = nickname;
        this.fnick = fnick;
        this.approve = approve;
    }
}
