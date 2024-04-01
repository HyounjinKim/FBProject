package com.firstproject.project.project.follow;

import com.firstproject.project.project.login.User;
import com.firstproject.project.project.login.UserDto;
import com.firstproject.project.project.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;
    private final UserRepository userRepository;

    //1 사용자가 다른 사용자를 팔로우하고, 이를 데이터베이스에 저장
    @PostMapping("/{followerNickname}/following/{followeeNickname}")
    public ResponseEntity<String> follow(@PathVariable String followerNickname, @PathVariable String followeeNickname) {
        User follower = userRepository.findByNickname(followerNickname)
                .orElseThrow(() -> new RuntimeException("Error: Follower not found"));
        User followee = userRepository.findByNickname(followeeNickname)
                .orElseThrow(() -> new RuntimeException("Error: Followee not found"));
        followService.follow(followerNickname, followeeNickname);
        String message = followerNickname + "가 " + followeeNickname + "의 친구가 되었습니다.";

        return ResponseEntity.ok(message);
    }

    //2 사용자가 다른 사용자를 언팔로우
    @DeleteMapping("/{followeeNickname}/unfollowing/{followerNickname}")
    public ResponseEntity<String> unfollow(@PathVariable String followerNickname, @PathVariable String followeeNickname) {
        followService.unfollow(followerNickname, followeeNickname);
        String message = followeeNickname + "가 " + followerNickname + "와 친구관계를 삭제하였습니다.";
        return ResponseEntity.ok(message);
    }

    //3 특정 사용자가 팔로잉하는 다른 사용자 목록을 가져옵니다. {Nickname} 경로 변수를 통해 사용자의 아이디를 전달받습니다.
    @GetMapping("/{nickname}/followings")
    public ResponseEntity<List<UserDto>> getFollowings(@PathVariable String nickname) {
        Optional<User> optionalUser = userRepository.findByNickname(nickname);
        System.out.println(optionalUser);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new ResponseEntity<>(followService.getFollowings(nickname), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //4 특정 사용자를 팔로우하는 다른 사용자 목록을 가져옵니다.
    @GetMapping("/{nickname}/followers")
    public ResponseEntity<List<UserDto>> getFollowers(@PathVariable String nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("Error : User is not found"));
        return new ResponseEntity<>(followService.getFollowers(nickname), HttpStatus.OK);
    }
}
