package com.mh.restapi03.friend;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendApprovalDto {

    private int fidx;
    private Approve approve;


    public FriendApprovalDto(int fidx, Approve approve) {
        this.fidx = fidx;
        this.approve = approve;
    }

    public int getFidx() {
        return fidx;
    }

    public void setFidx(int fidx) {
        this.fidx = fidx;
    }

    public Approve getApprove() {
        return approve;
    }

    public void setApprove(Approve approve) {
        this.approve = approve;
    }
}