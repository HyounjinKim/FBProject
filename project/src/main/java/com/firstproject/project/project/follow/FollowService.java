package com.firstproject.project.project.follow;

import com.firstproject.project.project.login.User;
import com.firstproject.project.project.login.UserDto;
import com.firstproject.project.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    //1 사용자가 다른 사용자를 팔로우하고, 이를 데이터베이스에 저장
    @Transactional
    public void follow(String followerNickname, String followeeNickname) {
        User follower = userRepository.findByNickname(followerNickname)
                .orElseThrow(() -> new RuntimeException("Error : Follower is not found"));
        User followee = userRepository.findByNickname(followeeNickname)
                .orElseThrow(() -> new RuntimeException("Error : Followee is not found"));
        validationCheck(follower, followee);
        Follow follow = new Follow(follower, followee);
        followRepository.save(follow);
    }

    //2 사용자가 다른 사용자를 언팔로우
    @Transactional
    public void unfollow(String followerNickname, String followeeNickname) {
        User follower = userRepository.findByNickname(followerNickname)
                .orElseThrow(() -> new RuntimeException("Error : Follower is not found"));
        User followee = userRepository.findByNickname(followeeNickname)
                .orElseThrow(() -> new RuntimeException("Error : Followee is not found"));
        Follow follow = followRepository.findByFollowerAndFollowee(follower, followee)
                .orElseThrow(() -> new RuntimeException("Error : Follow is not found"));
        followRepository.delete(follow);
    }

    //3 특정 사용자가 팔로잉하는 다른 사용자 목록을 가져옵니다. {Nickname} 경로 변수를 통해 사용자의 아이디를 전달받습니다.
    @Transactional
    public List<UserDto> getFollowings(String nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("Error : User is not found"));

        List<Follow> followings = followRepository.findByFollower(user);

        return followings.stream()
                .map(follow -> mapToDto(follow.getFollowee()))
                .collect(Collectors.toList());
    }

    //4 특정 사용자를 팔로우하는 다른 사용자 목록을 가져옵니다.
    @Transactional
    public List<UserDto> getFollowers(String nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("Error : User is not found"));
        List<Follow> followers = followRepository.findByFollowee(user);
        return followers.stream()
                .map(follow -> mapToDto(follow.getFollower()))
                .collect(Collectors.toList());
    }

    private UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setPasswordch(user.getPasswordch());
        userDto.setEmail(user.getEmail());
        userDto.setNickname(user.getNickname());
        userDto.setName(user.getName());
        userDto.setGender(user.getGender());
        userDto.setBirthdate(user.getBirthdate());
        userDto.setPhonenumber(user.getPhonenumber());
        userDto.setUser_date(user.getUser_date());
        userDto.setResign(user.getResign());
        userDto.setHeight(user.getHeight());
        userDto.setWeight(user.getWeight());

        return userDto;
    }

    private void validationCheck(User user, User followee) {
        if (user == followee) {
            throw new RuntimeException("Error : Cannot follow");
        }
        followRepository.findByFollowerAndFollowee(user, followee)
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 팔로우 되었습니다.");
                });
    }
}
