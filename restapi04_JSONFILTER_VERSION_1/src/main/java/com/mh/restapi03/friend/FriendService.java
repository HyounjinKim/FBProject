package com.mh.restapi03.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    public Friend addFriend(Friend friend){
        Friend nicknameFriend = friendRepository.findByFriendNickname(friend.getNickname());
        if (nicknameFriend != null) {
            return null;
        }
        Friend dbFriend = friendRepository.save(friend);
        return dbFriend;
    }

    public List<Friend> getAllFriends() {
        List<Friend> list = friendRepository.findAll();
        return list;
    }

    public Friend getFriendByNickname(String nickname) {
        return friendRepository.findByFriendNickname(nickname);
    }

    public void delete(int id) {
        Optional<Friend> dbFriendOptional = friendRepository.findById(id);
        dbFriendOptional.ifPresent(friendRepository::delete);
    }

    public Friend addFriendApproval(FriendApprovalDto friendApprovalDto) {
        Friend friend = friendRepository.findById(friendApprovalDto.getFidx()).orElse(null);
        if (friend == null) {
            throw new IllegalArgumentException("Friend with fidx " + friendApprovalDto.getFidx() + " not found");
        }
        friend.setApprove(friendApprovalDto.getApprove());
        return friendRepository.save(friend);
    }

    public Friend updateFriendApproval(int fidx, FriendApprovalDto friendApprovalDto) {
        Friend friend = friendRepository.findById(fidx).orElse(null);
        if (friend == null) {
            return null;
        }
        friend.setApprove(friendApprovalDto.getApprove());
        return friendRepository.save(friend);
    }

}

