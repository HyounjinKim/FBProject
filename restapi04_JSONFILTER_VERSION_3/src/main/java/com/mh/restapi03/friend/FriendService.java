package com.mh.restapi03.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    public FriendDto addFriend(FriendDto friendDto){
        Friend nicknameFriend = friendRepository.findByFriendNickname(friendDto.getNickname());
        if (nicknameFriend != null) {
            return null;
        }
        Friend friend = new Friend(friendDto.getFidx(), friendDto.getNickname(), friendDto.getFnick(), friendDto.getApprove());
        Friend dbFriend = friendRepository.save(friend);
        return new FriendDto(dbFriend.getFidx(), dbFriend.getNickname(), dbFriend.getFnick(), dbFriend.getApprove());
    }

    public List<FriendDto> getAllFriends() {
        List<Friend> list = friendRepository.findAll();
        List<FriendDto> friendDtoList = new ArrayList<>();
        for (Friend friend : list) {
            friendDtoList.add(new FriendDto(friend.getFidx(), friend.getNickname(), friend.getFnick(), friend.getApprove()));
        }
        return friendDtoList;
    }

    public FriendDto getFriendByNickname(String nickname) {
        Friend friend = friendRepository.findByFriendNickname(nickname);
        if (friend == null) {
            return null;
        }
        return new FriendDto(friend.getFidx(), friend.getNickname(), friend.getFnick(), friend.getApprove());
    }

    public void delete(int id) {
        Optional<Friend> dbFriendOptional = friendRepository.findById(id);
        dbFriendOptional.ifPresent(friendRepository::delete);
    }

    public FriendDto addFriendApproval(FriendDto friendDto) {
        Friend friend = friendRepository.findById(friendDto.getFidx()).orElse(null);
        if (friend == null) {
            throw new IllegalArgumentException("Friend with fidx " + friendDto.getFidx() + " not found");
        }
        friend.setApprove(friendDto.getApprove());
        friend = friendRepository.save(friend);
        return new FriendDto(friend.getFidx(), friend.getNickname(), friend.getFnick(), friend.getApprove());
    }

    public FriendDto updateFriendApproval(int fidx, FriendDto friendDto) {
        Friend friend = friendRepository.findById(fidx).orElse(null);
        if (friend == null) {
            return null;
        }
        friend.setApprove(friendDto.getApprove());
        friend = friendRepository.save(friend);
        return new FriendDto(friend.getFidx(), friend.getNickname(), friend.getFnick(), friend.getApprove());
    }
}


