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

    public FriendDto(int fidx, String nickname, String fnick) {
        this.fidx = fidx;
        this.nickname = nickname;
        this.fnick = fnick;
    }

    public FriendDto() {
    }

    public int getFidx() {
        return fidx;
    }

    public void setFidx(int fidx) {
        this.fidx = fidx;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFnick() {
        return fnick;
    }

    public void setFnick(String fnick) {
        this.fnick = fnick;
    }

    public Approve getApprove() {
        return approve;
    }

    public void setApprove(Approve approve) {
        this.approve = approve;
    }
}
